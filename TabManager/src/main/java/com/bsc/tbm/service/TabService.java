/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsc.tbm.service;

import com.bsc.tbm.util.SupportType;
import com.bsc.tbm.vo.TabCell;
import com.bsc.tbm.vo.TabRow;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class TabService {

    private static final Logger log = LoggerFactory.getLogger(TabService.class);
    private Map<Class<?>, Map<Integer, Object>> tabDataMap = Collections.synchronizedMap(new HashMap<Class<?>, Map<Integer, Object>>());

    public final <T> List<T> getTabDataList(Class<T> clazz) {

        Map<Integer, Object> tabDatas = tabDataMap.get(clazz);
        if (tabDatas == null || tabDatas.isEmpty()) {
            try {
                throw new Throwable("没有字节码类 class = " + clazz.getName() + " 对应的码表数据！！请先注册码表类！！");
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        return Collections.unmodifiableList(new ArrayList(tabDataMap.values()));

    }

    public final <T> Map<Integer, T> getTabDataMap(Class<T> clazz) {

        Map<Integer, Object> tabDatas = tabDataMap.get(clazz);
        if (tabDatas == null || tabDatas.isEmpty()) {
            try {
                throw new Throwable("没有字节码类 class = " + clazz.getName() + " 对应的码表数据！！请先注册码表类！！");
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }

        return Collections.unmodifiableMap((Map<Integer, T>) tabDatas);
    }

    public final <T> T getTabData(Class<T> clazz, int keyId) {

        Map<Integer, Object> tabDatas = tabDataMap.get(clazz);
        if (tabDatas == null || tabDatas.isEmpty()) {
            try {
                throw new Throwable("没有字节码类 class = " + clazz.getName() + " 对应的码表数据！！请先注册码表类！！");
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        Object data = tabDatas.get(keyId);
        if (data == null) {
            try {
                throw new Throwable("字节码类 class = " + clazz.getName() + " 的数据 没有 id = " + keyId + " 的数据对象！！！");
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        return (T) data;
    }

    protected void initTabObjData(List<TabRow> rows, String tabName, Map<String, Class<?>> clazzMap) throws Throwable {

        Class<?> clazz = clazzMap.get(tabName);
        if (clazz == null) {
            throw new Throwable("表 tabName = " + tabName + " 没有找到对象的注册对象！！！！");
        }
        Map<Integer, Object> dataMap = new HashMap<Integer, Object>(rows.size());
        for (TabRow row : rows) {
            Object data = clazz.newInstance();
            Integer key = setObjData(data, (Class<Object>) clazz, row);
            if (key > 0) {
                dataMap.put(key, data);
            }
        }
        tabDataMap.put(clazz, dataMap);
        log.info("######################表 tabName = " + tabName + " 成功载入!!!");
    }

    private Integer setObjData(Object data, Class<Object> clazz, TabRow row) {

        Field[] fields = clazz.getDeclaredFields();
        List<TabCell> cells = row.getTabCells();
        Integer key = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            Integer result = dosetData(data, field, cells);
            if (result > 0) {
                key = result;
            }
            field.setAccessible(false);
        }
        return key;
    }

    private Integer dosetData(Object data, Field field, List<TabCell> cells) {
        
        String fieldName = field.getName();
        for (TabCell cell : cells) {
            
            if (cell.getValue().contains("##")) {
                continue;
            }
            
            String sectName = doWithStr(cell.getTitle());
            if (fieldName.equalsIgnoreCase(sectName)) {
                try {
                    return SupportType.getInstance().setData(data, field, cell);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }
        return 0;
    }

    private String doWithStr(String sectName) {
        while (sectName.contains("_")) {
            sectName = sectName.replace("_", "");
        }
        return sectName;
    }
}
