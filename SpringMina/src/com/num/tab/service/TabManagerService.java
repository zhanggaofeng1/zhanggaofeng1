/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.tab.service;

import com.num.tab.utils.TabUtil;
import com.num.tab.vo.AbstTab;
import com.num.tab.vo.UserInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javolution.util.FastMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

/**
 *
 * @author Administrator
 */
@DependsOn({"tabService"})
public class TabManagerService implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(TabManagerService.class);
    private static final FastMap<String, Class<? extends AbstTab>> registerClazzs = new FastMap<>();
    private String tabPath;
    @Autowired
    private TabService tabService;

    public TabManagerService(String tabPath) {
        this.tabPath = tabPath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        register();
        load(null);
    }

    private void register() {
        registerClazzs.put("userInfo.tab", UserInfo.class);
    }

    public boolean reload(List<String> names) throws IllegalArgumentException {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("重新载入tab表，参数为空， 没有要载入的表！！！");
        }
        load(names);
        return true;
    }

    private void load(List<String> names) {

        try {
            File tabFile = new File(tabPath);

            if (!tabFile.exists()) {
                tabFile.mkdirs();
            }

            File[] files = tabFile.listFiles();
            if (files.length <= 0) {
                log.debug("tab文件夹下的tab文件个数为 0 ！！！！");
                return;
            }

            for (File file : tabFile.listFiles()) {

                String fileName = file.getName();
                if (!fileName.endsWith(".tab")) {
                    throw new Throwable("tab表文件夹里出现了非tab文件 name = " + fileName);
                }

                // 判读是否是重载调用
                if (names != null) {
                    if (names.isEmpty()) {
                        break;
                    }
                    if (!names.contains(fileName)) {
                        continue;
                    }
                    names.remove(fileName);
                }

                Class<? extends AbstTab> clazz = registerClazzs.get(fileName);
                if (clazz == null) {
                    throw new Throwable("没有找到fileName = " + fileName + " 对应的tab对象 objName = " + clazz.getName());
                }

                FileInputStream input = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf8"));

                String[] titles = getStrArray(reader.readLine());// 英文标题
                String[] types = getStrArray(reader.readLine());// 类型注释
                if (titles.length != types.length) {
                    throw new Throwable("表 name = " + fileName + " 标题和类型的列数不同！！");
                }

                FastMap<Integer, AbstTab> data = new FastMap<>();
                String line = null;
                int i = 4;
                while ((line = reader.readLine()) != null) {

                    String[] dataArr = line.split("\t");
                    if (titles.length != dataArr.length) {
                        throw new Throwable("表 name = " + fileName + " 的第 " + i + " 行数据和title的列数不相等！！");
                    }
                    AbstTab abstTab = linePackageAbstTab(fileName, titles, types, dataArr, clazz);
                    data.put(abstTab.getKey(), abstTab);
                    i++;
                }

                reader.close();
                input.close();

                tabService.addTabData(clazz, data);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    private AbstTab linePackageAbstTab(String fileName, String[] titles, String[] types, String[] dataArr, Class<? extends AbstTab> clazz) throws InstantiationException, IllegalAccessException, Throwable {

        TabUtil tabLine = new TabUtil();

        int titleLen = types.length;
        for (int i = 0; i < titleLen; i++) {
            tabLine.realPackageAbstTab(titles[i], types[i], dataArr[i], fileName, i + 1);
        }

        AbstTab abstTab = clazz.newInstance();
        abstTab.initData(tabLine);
        if (abstTab.getKey() <= 0) {
            throw new Throwable("tab表对象 " + clazz.getName() + " 的 keyId 属性字段没有设置值！！！");
        }

        return abstTab;
    }

    private String[] getStrArray(String data) {
        return data.split("\t");
    }
}
