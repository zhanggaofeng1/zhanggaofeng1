/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.tab.service;

import com.num.tab.vo.AbstTab;
import java.util.Collection;
import javolution.util.FastList;
import javolution.util.FastMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class TabService {

    private final FastMap<Class<? extends AbstTab>, FastMap<Integer, AbstTab>> tabData = new FastMap<Class<? extends AbstTab>, FastMap<Integer, AbstTab>>().setShared(true);

    public final <T extends AbstTab> T getAbstTab(Integer mapKey, Class<T> clazz) {

        try {
            FastMap<Integer, AbstTab> data = tabData.get(clazz);
            if (data == null) {
                throw new Throwable("没有类name = " + clazz.getName() + " 对应的码表数据！！！！");
            }
            AbstTab tab = data.get(mapKey);
            if (tab == null) {
                throw new Throwable("类name = " + clazz.getName() + " 对应的码表数据中没有 mapkey = " + mapKey + " 对应的对象！！！");
            }
            return (T) tab;
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public final <T extends AbstTab> FastMap<Integer, T> getAbstTabMap(Class<T> clazz) {

        try {
            FastMap<Integer, AbstTab> data = tabData.get(clazz);
            if (data == null) {
                throw new Throwable("没有类name = " + clazz.getName() + " 对应的码表数据！！！！");
            }
            return (FastMap<Integer, T>) data;
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public final <T extends AbstTab> FastList<T> getAbstTabList(Class<T> clazz) {

        try {
            FastMap<Integer, AbstTab> data = tabData.get(clazz);
            if (data == null) {
                throw new Throwable("没有类name = " + clazz.getName() + " 对应的码表数据！！！！");
            }
            return (FastList<T>) new FastList(data.values());
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public final <T extends AbstTab> Collection<T> getAbstTabCollection(Class<T> clazz) {

        try {
            FastMap<Integer, AbstTab> data = tabData.get(clazz);
            if (data == null) {
                throw new Throwable("没有类name = " + clazz.getName() + " 对应的码表数据！！！！");
            }
            return (Collection<T>) data.values();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return null;
    }

    protected void addTabData(Class<? extends AbstTab> clazz, FastMap<Integer, AbstTab> data){
        tabData.put(clazz, data);
    }
}
