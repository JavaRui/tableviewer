package com.crwu.swt.common.base;

import com.crwu.swt.common.util.SwtVoid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cr.wu
 *
 * 2015年7月30日
 */

public class YtComposite extends Composite {
	protected YtComposite me;
	
	
	public YtComposite(Composite parent, int style) {
		super(parent, style);
		
		me = this;
		addPaint();
//		setBackground(YtColorUtil.bgColor);
		if(parent instanceof YtComposite){
			modularId = ((YtComposite) parent).getModularId();
		}
	}
	
	public YtComposite(Composite parent) {
		this(parent, 0);
	}
	

	
	private int modularId = 0;

	public int getModularId() {
		return modularId;
	}

	public void setModularId(int modularId) {
		this.modularId = modularId;
		Control[] cs = getChildren();
		for(int i = 0 ; i < cs.length ; i++ ){
			if(cs[i] instanceof YtComposite){
				((YtComposite)cs[i]).setModularId(modularId);
			}
		}
	}
	
	public List<?> getChildByClass(Class<?> cls){
		List<Control> csList = new ArrayList<Control>();
		Control[] cs = getChildren();
		for(int i = 0 ; i < cs.length ; i++ ){
			if(cs[i].getClass().getSimpleName().equals(cls.getSimpleName()) ){
				((YtComposite)cs[i]).setModularId(modularId);
				csList.add(cs[i]);
			}
		}
		return csList;
	} 
	
	/**
	 * 创建一个label
	 * @param txt
	 * @return
	 */
	public Label createLabel(String txt) {
		return createLabel(txt,0);
	}
	
	/**
	 * 创建一个label
	 * @param txt
	 * @return
	 */
	public Label createLabel(String txt , int style) {
		Label label = new Label(this, style);
		label.setText(txt);
		return label;
	}
	
	
	
	
	public void lll(Object text){
		System.out.println(text);
	}
	
	public void lle(Object text){
		System.err.println(text);
	}
	/**
	 * 根据getData("name")获取相应的子控件 能获取的子控件，必须有名字
	 * 
	 * @param data
	 *            名字
	 * */
	public Control getChildByName(Object data) {
		
		return getChildByData("name",data);
	}
	
	/**
	 * 根据子控件层次获取子控件
	 * @param index
	 * @return
	 */
	public Control getChildIndex(int index){
		return getChildren()[index];
	}
	
	/**
	 * 获取第一个子控件
	 * @return
	 */
	public Control getFirstChild(){
		return getChildren()[0];
	}
	
	/**
	 * 添加绘图事件
	 */
	private void addPaint(){
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				
				afterInit();
				removePaintListener(this);
			}
		});
	}
	
	/**
	 * 绘图事件执行的界面，可用于复写，在此方法执行的时候，所有的控件都将被分配了bound。可以直接设置bound
	 */
	protected void afterInit() {
		
	}

	/**
	 * 获取子控件数量
	 * */
	public int getChildNum(){
		return getChildren().length;
	}
	
	
	/**
	 * 设置成gridlayout
	 */
	public void setGridLayout(){
		setLayout(new GridLayout());
	}
	
	/**
	 * 封装了setLayout(new GridLayout(grid , b));
	 * @param grid
	 * @param b
	 */
	public void setGridLayout(int grid , boolean b){
		setLayout(new GridLayout(grid , b));
	}
	
	/**
	 * 设置充满式布局
	 */
	public void setFillLayout(){
		setLayout(new FillLayout());
	}
	
	/**
	 * 设置griddata样式
	 * @param hor 横向是否充满
	 * @param ver 竖向是否充满
	 */
	public void setGd(boolean hor , boolean ver){
		
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, hor, ver);
		me.setLayoutData(gridData);
		
		
	}
	
	
	/**
	 * 根据子控件的属性值配对，获取子控件
	 * */
	public Control getChildByData(String dataName , Object dataValue){
		Control[] controls = this.getChildren();
		for (int i = 0; i < controls.length; i++) {
			if (dataValue.equals(controls[i].getData(dataName) )) {
				return controls[i];
			}
		}
		return null;
	}
	/**
	 * 设置子控件是否接收鼠标事件
	 * */
	public void setChildEnabled(boolean enabled) {
		Control[] controls = this.getChildren();
		for (int i = 0; i < controls.length; i++) {
			controls[i].setEnabled(enabled);
		}
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		setChildEnabled(enabled);
		super.setEnabled(enabled);
	}
	
	/**
	 * 设置子控件是否接收鼠标事件
	 * */
	public void setChildVisible(boolean enabled) {
		Control[] controls = this.getChildren();
		for (int i = 0; i < controls.length; i++) {
			controls[i].setVisible(enabled);
		}
	}
	/**
	 * 设置所有的按钮的选中状态
	 * */
	public void setChildBtnSelect(boolean select) {
		Control[] controls = this.getChildren();
		for (int i = 0; i < controls.length; i++) {
			if(controls[i] instanceof Button){
				Button btn = (Button) controls[i];
				btn.setSelection(select);
			}
		}
	}
	
	public void removeAllChild(){
		SwtVoid.removeAllChild(me);
	}
	
	/**
	 * 获取子控件实际的尺寸，设置为显示的尺寸
	 * */
	public void autoSize() {
		Point xy = getRealSize();
		setSize(xy);
	}
	/**
	 * 获取真实的尺寸，但有可能因为被同级控件重叠，导致尺寸减少，具体原因还不是很清楚。
	 * */
	public Point getRealSize(){
		Point xy = computeSize(SWT.DEFAULT, SWT.DEFAULT);
		return xy;
	}
	/**
	 * 设置gridlayout，且layout的数量是子控件的数量
	 * */
	public void setGridLayoutByChildren(){
		setLayout(new GridLayout(getChildren().length, true));
	}
	/**
	 * 设置gridlayout，且layout的数量是子控件的数量,且宽度不会强制相同
	 * */
	public void setGridLayoutByChildren(boolean b){
		setLayout(new GridLayout(getChildren().length, b));
	}
	

	/****        保存子控件状态功能                          ***************/

	protected Map<Control, Boolean> childVisible = new HashMap();
	protected Map<Control, Boolean> childEnabled = new HashMap();
	protected Map<Control, Rectangle> childBound = new HashMap();
	/**
	 * 保存子控件当前状态
	 */
	public void saveChildState(){
		
		childVisible.clear();
		for(Control child : getChildren()){
			childVisible.put(child, child.getVisible());
			childEnabled.put(child, child.getEnabled());
			childBound.put(child, child.getBounds());
		}
	}
	
	/**
	 * 恢复字控件状态
	 */
	public void loadChildState(){
		for(Control child : getChildren()){
			if(child == null || child.isDisposed()){
				continue;
			}else if(childVisible.size() == 0){
				return ;
			}
			
			child.setVisible(childVisible.get(child));
			child.setEnabled(childEnabled.get(child));
			child.setBounds(childBound.get(child));
//			childBound.put(child, child.getBounds());
		}
	}
	
	
	
	
}

