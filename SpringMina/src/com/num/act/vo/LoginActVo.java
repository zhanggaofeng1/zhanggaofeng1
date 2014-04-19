/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.vo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class LoginActVo extends CommonActVo {

  private static final int version = 1;
  private List<Integer> uids = new ArrayList<>();

    public List<Integer> getUids() {
        return uids;
    }

    public void setUids(List<Integer> uids) {
        this.uids = uids;
    }
  
  @Override
  public String getParamJson() {
  
      String json = version + ",";
      for (int uid : uids) {
          json += uid + ",";
      }
      return json;
  }
  
  @Override
  public void setParamJson(String json) {
      String[] params = json.split(",");
      if (1 == Integer.parseInt(params[0])) {
          setParamJson1(params);
      }
  }
  
  private void setParamJson1(String[] params) {
      
      int len = params.length;
      for (int i = 1; i < len; i++) {
          uids.add(Integer.parseInt(params[i]));
      }
  }
}
