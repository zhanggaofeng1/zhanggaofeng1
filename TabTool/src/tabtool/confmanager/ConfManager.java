/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tabtool.putinfo;

import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class PutInfoService {
    
    public static final String execl_path = "execl_path";
    public static final String tab_path = "execl_path";
    private static PutInfoService putInfo = null;
    private Properties properties = null;
    
    private PutInfoService() {
        load();
    }
    
    private void load() {
    }
    
    public String getPropertyVal(String key) {
        return (String)properties.get(key);
    }
    
    public static PutInfoService getInstance() {
        
        if (putInfo == null) {
            putInfo = new PutInfoService();
        }
        return putInfo;
    }
    
    
    
}
