/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tabtool.confmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class ConfManager {

    public static final String excel_path = "execl_path";
    public static final String tab_path = "tab_path";
    private static ConfManager putInfo = null;
    private final String file_path = "./cfg/";
    private final String file_name = "tab.properties";
    private Properties props = new Properties();

    private ConfManager() {
        try {
            load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void load() throws IOException {
        File file = new File(file_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(file_path + file_name);
        if (!file.exists()) {
            file.createNewFile();
            return;
        }
        FileInputStream input = new FileInputStream(file);
        props.load(input);
        input.close();
    }

    public void save() {
        
        if (props.size() <= 0) {
            return;
        }
        
        try {
            FileOutputStream output = new FileOutputStream(file_path + file_name);
            props.store(output, tab_path + file_name);
            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getPropertyVal(String key) {
        return (String) props.get(key);
    }

    public void addPropertyVal(String key, String value) {
        props.put(key, value);
    }

    public static ConfManager getInstance() {

        if (putInfo == null) {
            putInfo = new ConfManager();
        }
        return putInfo;
    }
}
