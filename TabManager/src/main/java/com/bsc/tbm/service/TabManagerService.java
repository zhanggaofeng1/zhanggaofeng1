/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsc.tbm.service;

import com.bsc.protracted.domin.CdFat;
import com.bsc.protracted.domin.CdMap;
import com.bsc.protracted.domin.CdMonster;
import com.bsc.tbm.util.SupportType;
import com.bsc.tbm.vo.TabRow;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 *
 * @author zhanggaofeng
 */
@DependsOn({"tabService"})
@Service
public class TabManagerService implements InitializingBean {

    private static final String tab_path = "D:/usr/local/temp/tab/";
    private static final Logger log = LoggerFactory.getLogger(TabManagerService.class);
    private Map<String, Class<?>> tabClazzs = new HashMap<String, Class<?>>();
    @Autowired
    private TabService tabService;

    public void afterPropertiesSet() throws Exception {
        // 一下两行代码顺序不能调换
        register_tab();
        load_tab();
    }

    private void register_tab() {
        registerTab("fat", CdFat.class);
        registerTab("map", CdMap.class);
        registerTab("monster", CdMonster.class);
    }

    private void registerTab(String tabName, Class<?> tabClazz) {
        tabClazzs.put(tabName, tabClazz);
    }

    private void load_tab(String... tabNames) {

        File tabPath = new File(tab_path);
        try {
            if (!tabPath.exists()) {
                tabPath.mkdirs();
            }
            File[] files = tabPath.listFiles();
            if (files.length <= 0) {
                log.error("码表路径 path = " + tab_path + " 下 没有文件存在！！！！");
                return;
            }

            if (tabNames.length <= 0) {// 说明是启动时载入全部表
                load_tab_logic(null, files);
            } else {// 启动后，通过操作载入指定的表
                load_tab_logic(Arrays.asList(tabNames), files);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public void reload(String... tabNames) {

        if (tabNames.length <= 0) {
            return;
        }
        load_tab(tabNames);
    }

    private void load_tab_logic(List<String> reloadTabs, File... files) throws Throwable {

        boolean reloadOper = false;
        if (reloadTabs != null && !reloadTabs.isEmpty()) {
            reloadOper = true;
        }

        for (File file : files) {

            String tabPath = file.getName();
            if (!tabPath.endsWith(".xls") && !tabPath.endsWith(".xlsx")) {
                throw new Throwable("码表路径 path = " + tab_path + " 出现了非 excel 文件啦！！！！tabName = " + tabPath);
            }

            String tabName = getTabName(tabPath);
            if (reloadOper) {
                if (reloadTabs == null || reloadTabs.isEmpty()) {
                    break;
                }
                if (!reloadTabs.contains(tabName)) {
                    continue;
                }
                reloadTabs.remove(tabName);
            }

            Sheet sheet0 = getExeclSheet0(file, file.getName());
            List<TabRow> tabRows = parseSheet(sheet0, tabName);
            tabService.initTabObjData(tabRows, tabName, tabClazzs);
        }
    }

    private String getTabName(String tabPath) {
        int endPos = tabPath.lastIndexOf(".");
        return tabPath.substring(0, endPos);
    }

    public List<TabRow> parseSheet(Sheet sheet, String tabName) throws Throwable {

        int colNum = sheet.getRow(2).getLastCellNum();
        int rowNum = sheet.getLastRowNum();

        List<Integer> delCols = new ArrayList<Integer>();
        Map<Integer, String> titles = new HashMap<Integer, String>();
        Map<Integer, String> types = new HashMap<Integer, String>();
        List<TabRow> tabRowList = new ArrayList<TabRow>(colNum - 2);
        for (Integer i = 2; i < rowNum; i++) {

            TabRow tabRow = new TabRow();
            Row row = sheet.getRow(i);
            for (Integer j = 1; j < colNum; j++) {

                String cellStr = null;
                Cell cell = row.getCell(j);
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cellStr = cell.getStringCellValue();
                } else {
                    cellStr = "####";
                }

                if (i == 2) {
                    if (cellStr == null || cellStr.isEmpty() || cellStr.contains("##")) {
                        if (j == 1) {
                            throw new Throwable("tab = " + tabName + "码表的第一列不能作为删除列的，且必须是唯一性的！！！！");
                        }
                        delCols.add(j);
                    } else {
                        titles.put(j, cellStr);
                    }
                } else if (i == 3) {
                    if (!delCols.contains(j)) {
                        if (cellStr == null || cellStr.isEmpty()) {
                            throw new Throwable("第 " + (j + 1) + " 列的数据类型为空了 tabName = " + tabName);
                        }
                        if (!SupportType.getInstance().contains(cellStr)) {
                            throw new Throwable("第 " + (j + 1) + " 列的数据类型暂不支持 tabName = " + tabName);
                        }
                        if (j == 1 && !cellStr.equalsIgnoreCase("int")) {
                            throw new Throwable("表 tab = " + tabName + " 第一列必须为int type!!!!!");
                        }
                        types.put(j, cellStr);
                    }
                } else {
                    if (!delCols.contains(j)) {
                        if (cellStr == null || cellStr.isEmpty()) {
                            cellStr = "####";
                        }
                        if (j == 1) {
                            tabRow.addCell(true, titles.get(j), types.get(j), cellStr);
                        } else {
                            tabRow.addCell(false, titles.get(j), types.get(j), cellStr);
                        }
                    }
                }
            }
            if (i != 2 && i != 3) {
                tabRowList.add(tabRow);
            }
        }
        return tabRowList;
    }

    private Sheet getExeclSheet0(File file, String tabName) throws Exception {

        InputStream stream = new FileInputStream(file);
        Workbook wb = null;
        if (tabName.endsWith(".xlsx")) {
            wb = new XSSFWorkbook(stream);
        } else {
            wb = new HSSFWorkbook(stream);
        }
        stream.close();
        return wb.getSheetAt(0);
    }
}
