/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.tab.service;

import com.num.tab.vo.AbstTab;
import com.num.tab.utils.TabUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javolution.util.FastList;
import javolution.util.FastMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class TabInstance<T extends AbstTab> {

    private static final Logger log = LoggerFactory.getLogger(TabInstance.class);
    private final FastMap<Integer, T> datas = new FastMap<Integer, T>();
    private Class<T> clazz;
    private String filePath;
    private String fileName;

    public final FastList<T> getListTabObjs() {
        return new FastList(datas.values());
    }

    public final FastMap<Integer, T> getMapTabObjs() {
        return datas;
    }

    public final T getMapTabObj(Integer id) {
        return datas.get(id);
    }

    public TabInstance(String path, Class<T> clazz) {
        this.filePath = path;
        this.clazz = clazz;
        this.fileName = path.substring(path.lastIndexOf('/') + 1);
    }

    protected void reload() {
        synchronized (datas) {
            datas.clear();
            load();
        }
    }

    protected void load() {

        try {
            FileInputStream input = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf8"));

            String[] titles = getStrArray(reader.readLine());// 英文标题
            String[] types = getStrArray(reader.readLine());// 类型注释
            if (titles.length != types.length) {
                log.error("表 name = " + fileName + " 标题和类型的列数不同！！");
                System.exit(1);
            }

            String line = null;
            int i = 4;
            while ((line = reader.readLine()) != null) {

                String[] dataArr = line.split("\t");
                if (titles.length != dataArr.length) {
                    log.error("表 name = " + fileName + " 的第 " + i + " 行数据和title的列数不相等！！");
                    System.exit(1);
                }

                linePackageAbstTab(titles, types, dataArr);
                i++;
            }

            reader.close();
            input.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch (InstantiationException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private String[] getStrArray(String data) {
        return data.split("\t");

    }

    private void linePackageAbstTab(String[] titles, String[] types, String[] dataArr) throws InstantiationException, IllegalAccessException {

        TabUtil tab = new TabUtil();

        int titleLen = types.length;
        for (int i = 0; i < titleLen; i++) {
            tab.realPackageAbstTab(titles[i], types[i], dataArr[i], fileName, i + 1);
        }

        T t = clazz.newInstance();
        t.initData(tab);
        if (t.getKey() == 0) {
            log.error("tab表对象 " + t.getClass().getName() + " 的 keyId 属性字段没有设置值！！！");
            System.exit(1);
        }
        datas.put(t.getKey(), t);
    }
}