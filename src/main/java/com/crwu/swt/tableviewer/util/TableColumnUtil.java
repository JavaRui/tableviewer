package com.crwu.swt.tableviewer.util;

import com.crwu.swt.tableviewer.annotation.TableColumnSetting;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/23 11:14
 */
public class TableColumnUtil {

    public static Object parse(Object object , int index){

        Field field =  getField(object,index);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static Object parse(Object object , String column){

        Field field = getField(object, column);

        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Field getField(Object object , String column){

        List<Field> fieldList = getFieldList(object, TableColumnSetting.class);
        if(fieldList.isEmpty()){
            throw new RuntimeException("object:  "+object+"  中没有找到tableColumnSetting注解的属性");
        }

        for (Field field : fieldList) {
            TableColumnSetting annotation = field.getAnnotation(TableColumnSetting.class);
            if(annotation.columnText().equals(column)){
                field.setAccessible(true);
                return field;
            }
        }
        return null;

    }

    public static Field getField(Object object , int index){

        List<Field> fieldList = getFieldList(object, TableColumnSetting.class);
        if(fieldList.isEmpty()){
            throw new RuntimeException("object:  "+object+"  中没有找到tableColumnSetting注解的属性");
        }

        for (Field field : fieldList) {
            TableColumnSetting annotation = field.getAnnotation(TableColumnSetting.class);
            if(annotation.index() == index){
                field.setAccessible(true);
                return field;
            }
        }
        return null;

    }



    public static List<Field> getFieldList(Object object , Class annotionClz){
        return getFieldList(object.getClass(),annotionClz);
    }



    public static List<Field> getFieldList(Class<?> clazz , Class annotionClz){

        List<Field> list = new LinkedList();

//        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {

                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    Annotation annotation = field.getAnnotation(annotionClz);
                    if(annotation == null){
                        continue;
                    }

                    list.add(field);

                }


            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz =
                // clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        if(annotionClz == TableColumnSetting.class){
            list.sort(new Comparator<Field>() {
                @Override
                public int compare(Field o1, Field o2) {

                    TableColumnSetting j1 = o1.getAnnotation(TableColumnSetting.class);
                    TableColumnSetting j2 = o2.getAnnotation(TableColumnSetting.class);
                    return j1.index()-j2.index();

                }
            });
        }

        return list;

    }


    public static List<TableColumnSetting> getTableColumnSettingList(List<Field> list){
        List<TableColumnSetting> settings = new LinkedList<TableColumnSetting>();
        list.forEach(field -> {
            TableColumnSetting j1 = field.getAnnotation(TableColumnSetting.class);
            settings.add(j1);

        });
        return settings;

    }

    public static List<TableColumnSetting> getTableColumnSettingList(Class<?> object){
        List<Field> fieldList = getFieldList(object, TableColumnSetting.class);

        return getTableColumnSettingList(fieldList);

    }


}
