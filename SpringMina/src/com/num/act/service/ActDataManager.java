/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.service;

import com.num.act.dao.ActDataDao;
import com.num.act.enums.ActIdEnum;
import com.num.act.vo.AbstActVo;
import com.num.act.vo.LoginActVo;
import javax.annotation.PostConstruct;
import javolution.util.FastMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class ActDataManager {

    private static final Logger log = LoggerFactory.getLogger(ActDataManager.class);
    private FastMap<Integer, Class<? extends AbstActVo>> actClass = new FastMap<Integer, Class<? extends AbstActVo>>();
    private FastMap<String, AbstActVo> actData = new FastMap<String, AbstActVo>().setShared(true);
    @Autowired
    private ActDataDao actDataDao;

    @PostConstruct
    public void init() {
        actClass.put(ActIdEnum.login_act_id.value(), LoginActVo.class);
    }

    private String getActDataKey(Integer userId, Integer actId) {
        return userId + "_" + actId;
    }

    public <T extends AbstActVo> T getAbstActVo(Integer userId, Integer actId) throws Throwable {

        if (userId == null || userId <= 0 || actId == null || actId <= 0) {
            throw new Throwable("获取活动对象，闯入的参数，不对！！！！");
        }

        String key = getActDataKey(userId, actId);
        AbstActVo actVo = actData.get(key);
        if (actVo == null) {
            Class<T> clazz = (Class<T>) actClass.get(actId);
            if (clazz == null) {
                new Throwable("活动id = " + actId + " 的逻辑存储对象没有注册！！！！！");
            }
            actVo = clazz.newInstance();
            actData.put(key, actVo);
        }
        actVo.init();
        return (T) actVo;
    }
    
    private void loadFromDb(Integer userId) {
        actDataDao.loadFromDb(userId);
    }
}
