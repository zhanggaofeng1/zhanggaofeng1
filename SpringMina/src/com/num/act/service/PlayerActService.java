/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.service;

import com.alibaba.fastjson.JSON;
import com.num.act.dao.PlayerActDao;
import com.num.act.enums.ActIdEnum;
import com.num.act.vo.CommonActVo;
import com.num.act.vo.LoginActVo;
import java.util.List;
import java.util.logging.Level;
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
public class PlayerActService {

    private static final String key_spit = "_";
    private static final Logger log = LoggerFactory.getLogger(PlayerActService.class);
    private FastMap<Integer, Class<? extends CommonActVo>> actClass = new FastMap<>();
    private FastMap<String, CommonActVo> actData = new FastMap<String, CommonActVo>().setShared(true);
    @Autowired
    private PlayerActDao playerActDao;

    private String getActDataKey(Integer userId, Integer actId) {
        return userId + key_spit + actId;
    }

    @PostConstruct
    public void init() {
        // 说动数据对象必须先注册
        actClass.put(ActIdEnum.login_act_id.value(), LoginActVo.class);
    }

    public final <T extends CommonActVo> T getCommonActVoByKey(Integer userId, Integer actId) {

        if (userId == null || userId <= 0 || actId == null || actId <= 0) {
            throw new IllegalArgumentException("获取活动对象，闯入的参数，不对！！！！");
        }
        String key = getActDataKey(userId, actId);
        CommonActVo actVo = actData.get(key);
        if (actVo == null) {
            Class<T> clazz = (Class<T>) actClass.get(actId);
            if (clazz == null) {
                new Throwable("活动id = " + actId + " 的逻辑存储对象没有注册！！！！！");
            }
            try {
                actVo = clazz.newInstance();
                actData.put(key, actVo);
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        actVo.init_data(0, actId, null, actId, key);
        return (T) actVo;
    }

    public void loadPlayerActInfo(Integer userId) {

        List<CommonActVo> actVoList = playerActDao.loadPlayerActInfo(userId, actClass);
        for (CommonActVo actVo : actVoList) {
            actData.put(getActDataKey(userId, actVo.getActId()), actVo);
        }
    }

    public boolean savePlayerActInfo(Integer userId) {

        for (Integer actId : actClass.keySet()) {
            String key = getActDataKey(userId, actId);
            CommonActVo actVo = actData.get(key);
            if (actVo == null) {
                continue;
            }

            if (actId != actVo.getActId()) {
                log.error("用户id = " + userId + " 存储活动信息时，key中的活动id = " + actId + " 和 活动对象中的活动id = " + actVo.getActId() + "不相同！!");
            }

            if (!playerActDao.savePlayerActInfo(userId, actVo)) {
                log.error("用户id = " + userId + " ,活动id = " + actId + " 的活动数据数据库存储失败！！！ data = " + JSON.toJSONString(actVo));
            }
            actData.remove(key);
        }
        return true;
    }

    public void saveToDbAllUserActInfo() {

        for (String key : actData.keySet()) {

            CommonActVo actVo = actData.get(key);
            String[] info = key.split(key_spit);
            int userId = Integer.valueOf(info[0]);
            int actId = Integer.valueOf(info[1]);

            if (actId != actVo.getActId()) {
                log.error("用户id = " + userId + " 存储活动信息时，key中的活动id = " + actId + " 和 活动对象中的活动id = " + actVo.getActId() + "不相同！!");
            }

            if (!playerActDao.savePlayerActInfo(userId, actVo)) {
                log.error("用户id = " + userId + " ,活动id = " + actId + " 的活动数据数据库存储失败！！！ data = " + JSON.toJSONString(actVo));
            }
        }

    }
}
