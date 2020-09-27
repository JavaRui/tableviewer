package com.crwu.swt.tableviewer.provider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import java.util.List;
/**
 * @author cr.wu
 *
 * 2015-8-2
 */
public class TableBaseContentProvider implements IStructuredContentProvider{

    private Class<?> classObj ;

    public TableBaseContentProvider(Class<?> classObj) {
        this.classObj = classObj;
    }

    public TableBaseContentProvider(){

    }


    @Override
	public void dispose() {
		
	}
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		return ((List)inputElement).toArray();
	}
	
}

