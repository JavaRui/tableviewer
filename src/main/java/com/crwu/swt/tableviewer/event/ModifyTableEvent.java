package com.crwu.swt.tableviewer.event;

import org.eclipse.swt.widgets.Event;

/**
 * @author cr.wu
 *
 * 2015��8��6��
 */
public class ModifyTableEvent extends Event{

	public String fieldName;
	
	public Object newValue;
	
	
	public Object element;

	public Object oldValue;

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
