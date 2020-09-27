package com.crwu.swt.tableviewer.provider;
import com.crwu.swt.tableviewer.util.TableColumnUtil;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
/**
 * @author cr.wu
 * 
 *         2015-8-2
 */
public class TableBaseTableSorter extends ViewerSorter {
	private static final int ASCENDING = 0;
	private static final int DESCENDING = 1;
	private int order;
	protected int column;
	public void doSort(int column) {
		if (column == this.column) {
			order = 1 - order;
		} else {
			this.column = column;
			order = ASCENDING;
		}
	}
	/**
	 * ????
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {
		int sort = 0;
		// ??????¨º???
		Object object = TableColumnUtil.parse(e1, column);
		Object object2 = TableColumnUtil.parse(e2, column);
		//??¨¨b?
		//int,float,double¨¨b???¡±
		if ((object instanceof Integer) || (object instanceof Double)|| (object instanceof Float)) {
			sort = (int) (Float.valueOf(object + "") - Float.valueOf(object2+ ""));
		} else {//???¨¨b???¡±
			String result = "";
			String result2 = "";
			result = String.valueOf(object);
			result2 = String.valueOf(object2);
			sort = result.compareTo(result2);
		}
		if (order == DESCENDING) {
			sort = -sort;
		}
		return sort;
	}
}

