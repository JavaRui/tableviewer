package com.crwu.swt.tableviewer.event;

import org.eclipse.swt.widgets.Event;

/**
 * 文本更改的事件
 * */
public class ModifyTableEvent extends Event{
    /**
     * 更改的属性
     * */
	public String fieldName;
	/**
     * 新的value
     * */
	public Object newValue;
	
	/**
     * 更改的行对象
     * */
	public Object element;
    /**
     * 旧的value
     * */
	public Object oldValue;
    /**
     * 第几行
     * */
	public int index;

    @Override
    public String toString() {
        return "ModifyTableEvent{" +
                "fieldName='" + fieldName + '\'' +
                ", newValue=" + newValue +
                ", element=" + element +
                ", oldValue=" + oldValue +
                ", index=" + index +
                "} " + super.toString();
    }
}
