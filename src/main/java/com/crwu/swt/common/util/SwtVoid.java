package com.crwu.swt.common.util;
import com.crwu.swt.common.INBack;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SwtVoid {
	/**
	 * 删除所有子控件
	 * @param composite
	 */
	public static void removeAllChild(Composite composite){
		for(  ; composite.getChildren().length > 0;){
			composite.getChildren()[0].dispose();
		}
		composite.layout();
	}
	
	public static void createSwt(INBack inBack ){
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(850, 800);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout());
		
		inBack.callBack(shell);
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}
	
	public static String getField(CharSequence content, String regex, int index) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		String result = null;
		if (matcher.find())
			result = matcher.group(index);
		if (result != null)
			result.trim();
		pattern = null;
		matcher = null;
		return result;
	}
	
}

