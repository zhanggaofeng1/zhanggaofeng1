/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsc.tbm.util;

import com.bsc.tbm.vo.TabCell;
import com.bsc.tbm.enums.TypeEnums;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class SupportType {

    private static final SupportType supportType = new SupportType();
    private List<String> types = new ArrayList<String>();

    private SupportType() {
        types.add(TypeEnums.SUPPROT_INT.type());
        types.add(TypeEnums.SUPPROT_UP_STRING.type());
        types.add(TypeEnums.SUPPROT_STRING.type());
        types.add(TypeEnums.SUPPROT_FLOAT.type());
        types.add(TypeEnums.SUPPROT_UP_FLOAT.type());
        types.add(TypeEnums.SUPPROT_UP_DOUBLE.type());
        types.add(TypeEnums.SUPPROT_DOUBLE.type());
    }

    public static SupportType getInstance() {
        return supportType;
    }

    public boolean contains(String type) {
        return types.contains(type);
    }

    public Integer setData(Object data, Field field, TabCell cell) throws IllegalArgumentException, IllegalAccessException, Throwable {

        String type = cell.getType();
        String value = cell.getValue();
        String fieldType = field.getGenericType().toString();

        if (type.equalsIgnoreCase(TypeEnums.SUPPROT_UP_STRING.type())) {
            field.set(data, value);
        } else if (type.equalsIgnoreCase(TypeEnums.SUPPROT_INT.type())) {
            if (fieldType.equals(TypeEnums.SUPPROT_INT.type())) {
                field.setInt(data, Integer.parseInt(value));
            } else {
                field.set(data, Integer.valueOf(value));
            }
        } else if (type.equalsIgnoreCase(TypeEnums.SUPPROT_UP_FLOAT.type())) {
            if (fieldType.equals(TypeEnums.SUPPROT_FLOAT.type())) {
                field.setFloat(data, Float.parseFloat(value));
            } else {
                field.set(data, Float.valueOf(value));
            }
        } else if (type.equalsIgnoreCase(TypeEnums.SUPPROT_UP_DOUBLE.type())) {
            if (fieldType.equals(TypeEnums.SUPPROT_DOUBLE.type())) {
                field.setDouble(data, Double.parseDouble(value));
            } else {
                field.set(data, Double.valueOf(value));
            }
        } else {
            throw new Throwable("码表对象 name = " + data.getClass().getName() + " 属性名字 name = " + field.getName() + " 没有支持的类型！！！");
        }

        if (cell.isKey()) {
            return Integer.valueOf(value);
        }
        return 0;
    }
}
