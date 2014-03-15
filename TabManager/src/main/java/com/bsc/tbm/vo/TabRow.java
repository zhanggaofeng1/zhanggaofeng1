/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsc.tbm.vo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class TabRow {
    List<TabCell> tabCells = new ArrayList<TabCell>();
    
    public List<TabCell> getTabCells() {
        return tabCells;
    }
    
    public void addCell(boolean key, String title, String type, String value) {
        tabCells.add(new TabCell(key, title, type, value));
    }
}
