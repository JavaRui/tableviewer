package com.crwu.swt.tableviewer.annotation;

import java.lang.annotation.*;

/**
 * @author wuchengrui
 * @Description: 注解在bean的属性上，用来做table列的显示
 * @date 2020/9/23 11:04
 */
@Target({ ElementType.TYPE ,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TableColumnSetting {

    /**
     * 列的位置
     * */
    int index();
    /**
     * 表头文本
     * */
    String columnText();

    /**
     * 是否可编辑
     * */
    boolean modify() default false;
    /**
     * 表头宽度
     * */
    int width() default 100;




}
