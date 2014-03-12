/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.tab.service;

import com.num.tab.vo.AbstTab;
import com.num.tab.vo.UserInfo;
import java.lang.reflect.Field;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * @author ZhangGaoFeng
 */
@Service
public class TableService {

    public final TabInstance<UserInfo> userInfo_tab = new TabInstance<UserInfo>("src/tab/userInfo.tab", UserInfo.class);

    @PostConstruct
    public void startInit() {
        userInfo_tab.load();
    }

    public boolean reload(List<String> tabNames) {

        Class<TableService> clazz = (Class<TableService>) this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {

                TabInstance<? extends AbstTab> tabServer = (TabInstance<? extends AbstTab>) field.get(this);
                Class<TabInstance<? extends AbstTab>> classTab = (Class<TabInstance<? extends AbstTab>>) tabServer.getClass();

                Field fieldTab = classTab.getDeclaredField("fileName");
                fieldTab.setAccessible(true);
                String fileName = (String) fieldTab.get(tabServer);
                fieldTab.setAccessible(false);

                if (tabNames.contains(fileName)) {
                    tabServer.reload();
                    tabNames.remove(fileName);
                }

                if (tabNames.isEmpty()) {
                    break;
                }

            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (SecurityException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
}
