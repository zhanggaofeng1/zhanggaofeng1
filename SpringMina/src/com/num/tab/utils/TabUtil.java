/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.tab.utils;

import com.num.tab.types.TabInt2;
import com.num.tab.types.TabInt3;
import com.num.tab.types.TabInt4;
import javolution.util.FastMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class TabUtil {

    private static final Logger log = LoggerFactory.getLogger(TabUtil.class);
    private FastMap<String, Object> data;
    
    public TabUtil() {
        data = new FastMap<String, Object>();
    }

    public void realPackageAbstTab(String title, String type, String data, String fileName, int col) {

        title = title.trim();
        type = type.trim();
        data = data.trim();

        log.debug("tabName = " + fileName + " 第 " + col + " 列数据类型转换 data = " + data + " 转换成type = " + type);

        switch (type) {
            case "int":
                if (data.contains("##")) {
                    this.addData(title, Integer.valueOf(0));
                } else {
                    this.addData(title, Integer.valueOf(data));
                }
                break;
            case "float":
            case "Float":
                if (data.contains("##")) {
                    this.addData(title, Float.valueOf(0));
                } else {
                    this.addData(title, Float.valueOf(data));
                }
                break;
            case "double":
            case "Double":
                if (data.contains("##")) {
                    this.addData(title, Double.valueOf(0));
                } else {
                    this.addData(title, Double.valueOf(data));
                }
                break;
            case "int[]":
                if (data.contains("##")) {
                    this.addData(title, null);
                } else {
                    this.addData(title, getIntArrFromStr(data));
                }
                break;
            case "string":
            case "String":
                if (data.contains("##")) {
                    this.addData(title, null);
                } else {
                    this.addData(title, data);
                }
                break;
            case "string[]":
            case "String[]":
                if (data.contains("##")) {
                    this.addData(title, null);
                } else {
                    this.addData(title, getStrArrFromStr(data));
                }
                break;
            case "int2":
                if (data.contains("##")) {
                    this.addData(title, null);
                } else {
                    this.addData(title, getTabInt2FromStr(data));
                }
                break;
            case "int2[]":
                if (data.contains("##")) {
                    this.addData(title, null);
                } else {
                    this.addData(title, getTabInt2ArrFromStr(data));
                }
                break;
            case "int3":
                if (data.contains("##")) {
                    this.addData(title, null);
                } else {
                    this.addData(title, getTabInt3FromStr(data));
                }
                break;
            case "int3[]":
                if (data.contains("##")) {
                    this.addData(title, null);
                } else {
                    this.addData(title, getTabInt3ArrFromStr(data));
                }
                break;
            case "int4":
                if (data.contains("##")) {
                    this.addData(title, null);
                } else {
                    this.addData(title, getTabInt4FromStr(data));
                }
                break;
            case "int4[]":
                if (data.contains("##")) {
                    this.addData(title, null);
                } else {
                    this.addData(title, getTabInt4ArrayFromStr(data));
                }
                break;
            default:
                System.exit(1);
        }
    }

    private void addData(String column, Object data) {
        this.data.put(column, data);
    }

    private TabInt4[] getTabInt4ArrayFromStr(String data) {
        String[] strArr = getStrArrFromStr(data);
        int arrLen = strArr.length;
        TabInt4[] int4Arr = new TabInt4[arrLen];
        for (int i = 0; i < arrLen; i++) {
            int4Arr[i] = getTabInt4FromStr(strArr[i]);
        }
        return int4Arr;
    }

    private TabInt4 getTabInt4FromStr(String data) {
        String[] int4s = data.split(":");
        if (int4s.length != 4) {
            log.error("data = " + data + " 不是int4类型数据！！！！");
            System.exit(1);
        }
        TabInt4 int4 = new TabInt4();
        int4.setParam1(Integer.valueOf(int4s[0]));
        int4.setParam2(Integer.valueOf(int4s[1]));
        int4.setParam3(Integer.valueOf(int4s[2]));
        int4.setParam4(Integer.valueOf(int4s[3]));
        return int4;
    }

    private TabInt3[] getTabInt3ArrFromStr(String data) {
        String[] strArr = getStrArrFromStr(data);
        int arrLen = strArr.length;
        TabInt3[] int3Arr = new TabInt3[arrLen];
        for (int i = 0; i < arrLen; i++) {
            int3Arr[i] = getTabInt3FromStr(strArr[i]);
        }
        return int3Arr;
    }

    private TabInt3 getTabInt3FromStr(String data) {
        String[] int3s = data.split(":");
        if (int3s.length != 3) {
            log.error("data = " + data + " 不是int3类型数据！！！！");
            System.exit(1);
        }
        TabInt3 int3 = new TabInt3();
        int3.setParam1(Integer.valueOf(int3s[0]));
        int3.setParam2(Integer.valueOf(int3s[1]));
        int3.setParam3(Integer.valueOf(int3s[2]));
        return int3;
    }

    private TabInt2[] getTabInt2ArrFromStr(String data) {
        String[] strArr = getStrArrFromStr(data);
        int arrLen = strArr.length;
        TabInt2[] int2Arr = new TabInt2[arrLen];
        for (int i = 0; i < arrLen; i++) {
            int2Arr[i] = getTabInt2FromStr(strArr[i]);
        }
        return int2Arr;
    }

    private TabInt2 getTabInt2FromStr(String data) {
        String[] int2s = data.split(":");
        if (int2s.length != 2) {
            log.error("data = " + data + " 不是int2类型数据！！！！");
            System.exit(1);
        }
        TabInt2 int2 = new TabInt2();
        int2.setParam1(Integer.valueOf(int2s[0]));
        int2.setParam2(Integer.valueOf(int2s[1]));
        return int2;
    }

    private int[] getIntArrFromStr(String data) {

        String[] arrStr = getStrArrFromStr(data);
        int arrLen = arrStr.length;
        int[] arrInt = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arrInt[i] = Integer.valueOf(arrStr[i]);
        }
        return arrInt;
    }

    private String[] getStrArrFromStr(String data) {
        return data.split(",");

    }

    //************************************************************************************ abstTab子类封装使用
    public Integer getInt(String column) {
        return (Integer) data.get(column);
    }

    public Integer[] getIntArray(String column) {
        return (Integer[]) data.get(column);
    }

    public Float getFloat(String column) {
        return (Float) data.get(column);
    }

    public Float[] getFloatArray(String column) {
        return (Float[]) data.get(column);
    }

    public Double getDouble(String column) {
        return (Double) data.get(column);
    }

    public Double[] getDoubleArray(String column) {
        return (Double[]) data.get(column);
    }

    public String getString(String column) {
        return (String) data.get(column);
    }

    public String[] getStringArray(String column) {
        return (String[]) data.get(column);
    }

    public TabInt2 getTabInt2(String column) {
        return (TabInt2) data.get(column);
    }

    public TabInt2[] getTabInt2Array(String column) {
        return (TabInt2[]) data.get(column);
    }

    public TabInt3 getTabInt3(String column) {
        return (TabInt3) data.get(column);
    }

    public TabInt3[] getTabInt3Array(String column) {
        return (TabInt3[]) data.get(column);
    }

    public TabInt4 getTabInt4(String column) {
        return (TabInt4) data.get(column);
    }

    public TabInt4[] getTabInt4Array(String column) {
        return (TabInt4[]) data.get(column);
    }
}
