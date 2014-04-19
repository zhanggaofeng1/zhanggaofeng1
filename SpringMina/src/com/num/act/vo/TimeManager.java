/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TimeManager {
    
    private static final Calendar cal = Calendar.getInstance();
    
    public int getCurDay() {
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    public int getCurMonth() {
        return cal.get(Calendar.MONTH);
    }
    public int getCurYear() {
        return cal.get(Calendar.YEAR);
    }
    
    public String getCurDayStr() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    
}
