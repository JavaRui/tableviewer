package com.crwu.swt.tableviewer.provider;
import com.crwu.swt.tableviewer.util.TableColumnUtil;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
/**
 * @author cr.wu
 *
 * 2015-8-2
 */
public class TableBaseLabelProvider implements ITableLabelProvider{

    public TableBaseLabelProvider(Class clz){

    }

	
	@Override
	public void addListener(ILabelProviderListener listener) {
		
	}
	@Override
	public void dispose() {
		
	}
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}
	@Override
	public void removeListener(ILabelProviderListener listener) {
		
	}
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		
		
		return null;
	}
	
	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		return TableColumnUtil.parse(element, columnIndex)+"";
		
	}
}

