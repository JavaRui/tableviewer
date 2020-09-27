package com.crwu.swt.tableviewer.provider;

import com.crwu.swt.tableviewer.annotation.TableColumnSetting;
import com.crwu.swt.tableviewer.event.ModifyFace;
import com.crwu.swt.tableviewer.event.ModifyTableEvent;
import com.crwu.swt.tableviewer.util.TableColumnUtil;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Item;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author cr.wu
 *
 * 2015-8-2
 */
public class TableBaseModify implements ICellModifier{
	private String[] canModify;
	private TableViewer tableViewer;
	
	/**table值被修改之后，抛出事件**/
	public static final int TABLE_MODIFY = 1001;
	
	
	/**
	 * @param tableViewer
	 * @param classObj
	 */
	public TableBaseModify(TableViewer tableViewer , Class<?> classObj){
		this.tableViewer = tableViewer;
		
		init(classObj);
	}
	
	private void init(Class<?> element){


        List<Field> fieldList = TableColumnUtil.getFieldList(element, TableColumnSetting.class);
        List<TableColumnSetting> tableColumnSettingList = TableColumnUtil.getTableColumnSettingList(fieldList);


        //设置可编辑的columnIndex
        CellEditor[] editors = new CellEditor[fieldList.size()];

        for (int i = 0; i < tableColumnSettingList.size(); i++) {
            TableColumnSetting tableColumnSetting = tableColumnSettingList.get(i);
            if(tableColumnSetting.modify()){
                editors[i] = new TextCellEditor(tableViewer.getTable());
            }else{
                editors[i] = new TextCellEditor(tableViewer.getTable());
            }
        }

		tableViewer.setCellEditors(editors);
	}
	
	@Override
	public void modify(Object element, String property, Object newValue) {
		if(element instanceof Item){
			element = ((Item)element).getData();
		}
		try {
            Field field = TableColumnUtil.getField(element, property);
            //通过文字，获取对应的属??
			String fieldName = field.getName();
			
			Object oldValue = field.get(element);
			
			if(oldValue.equals(newValue)){
				return ;
			}
			//设置输入的??
//            field.setAccessible(true);
			field.set(element,newValue);

            TableColumnSetting annotation = field.getAnnotation(TableColumnSetting.class);
            int index = annotation.index();
			
			
			ModifyTableEvent event = new ModifyTableEvent();
			event.fieldName = fieldName;
			event.newValue = newValue;
			event.oldValue = oldValue;
			event.element = element;
			event.index = index;
			
			if(element instanceof ModifyFace){
				((ModifyFace)element).modify(fieldName, newValue);
			}
			tableViewer.refresh();
			tableViewer.getTable().notifyListeners(TABLE_MODIFY, event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 编辑之前获取??
	 * */
	@Override
	public Object getValue(Object element, String property) {
        Field field = TableColumnUtil.getField(element, property);
        Object o = null;
        try {
            o = field.get(element);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;

	}
	
	@Override
	public boolean canModify(Object element, String property) {
		
		return true;
	}
}

