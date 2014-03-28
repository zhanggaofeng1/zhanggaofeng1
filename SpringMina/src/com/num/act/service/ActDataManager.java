/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.service;

import com.alibaba.fastjson.JSON;
import com.num.act.dao.ActDataDao;
import com.num.act.enums.ActIdEnum;
import com.num.act.vo.AbstActVo;
import com.num.act.vo.CommonAbstActVo;
import com.num.act.vo.TestCommonActVo;
import com.num.player.vo.Player;
import java.util.List;
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

    private static final String key_spit = "_";
    private static final Logger log = LoggerFactory.getLogger(ActDataManager.class);
    private FastMap<Integer, Class<? extends AbstActVo>> actClass = new FastMap<Integer, Class<? extends AbstActVo>>();
    private FastMap<String, AbstActVo> actData = new FastMap<String, AbstActVo>().setShared(true);
    @Autowired
    private ActDataDao actDataDao;

    @PostConstruct
    public void init() {
        // 活动数据对象必须先注册
        actClass.put(ActIdEnum.login_act_id.value(), TestCommonActVo.class);
    }

    private String getActDataKey(Integer userId, Integer actId) {
        return userId + key_spit + actId;
    }

    public final <T extends AbstActVo> T getAbstActVo(Integer userId, Integer actId) throws Throwable {

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
            actVo.fromDbInit(0, actId);
            actData.put(key, actVo);
        }
        actVo.init();

        return (T) actVo;
    }

    public void loadFromDb(Integer userId) {

        List<AbstActVo> actVoList = actDataDao.loadFromDb(userId, actClass);
        for (AbstActVo actVo : actVoList) {
            actData.put(getActDataKey(userId, actVo.curActId()), actVo);
        }
    }

    public boolean saveActData(int userId, AbstActVo actVo) {
        return actDataDao.saveToDb(userId, actVo);
    }

    public void playerOffline(Player player) throws Throwable {
        saveToDb(player.getPlayerId());
    }

    private boolean saveToDb(Integer userId) {

        for (Integer actId : actClass.keySet()) {
            String key = getActDataKey(userId, actId);
            AbstActVo actVo = actData.get(key);
            if (actVo == null) {
                continue;
            }

            if (actId != actVo.curActId()) {
                log.error("用户id = " + userId + " 存储活动信息时，key中的活动id = " + actId + " 和 活动对象中的活动id = " + actVo.curActId() + "不相同！!");
            }

            if (!saveActData(userId, actVo)) {
                log.error("用户id = " + userId + " ,活动id = " + actId + " 的活动数据数据库存储失败！！！ data = " + JSON.toJSONString(actVo));
            }
        }
        return true;
    }

    public void saveToDbAllUserActInfo() {

        for (String key : actData.keySet()) {

            AbstActVo actVo = actData.get(key);
            if (actVo instanceof CommonAbstActVo) {// 共有的活动数据不在此存储
                continue;
            }
            String[] info = key.split(key_spit);
            int userId = Integer.valueOf(info[0]);
            int actId = Integer.valueOf(info[1]);

            if (actId != actVo.curActId()) {
                log.error("用户id = " + userId + " 存储活动信息时，key中的活动id = " + actId + " 和 活动对象中的活动id = " + actVo.curActId() + "不相同！!");
            }

            if (!actDataDao.saveToDb(userId, actVo)) {
                log.error("用户id = " + userId + " ,活动id = " + actId + " 的活动数据数据库存储失败！！！ data = " + JSON.toJSONString(actVo));
            }
        }
    }

    public void saveToDbForCommonActData(int common_id, int actId) {
        
        String key = getActDataKey(common_id, actId);
        AbstActVo actVo = actData.get(key);
        if (actVo == null) {
            return;
        }
        if (!(actVo instanceof CommonAbstActVo)) {
            log.error("活动id = " + actId + " 不是共有的活动数据！！！");
        }
        CommonAbstActVo comAbstAct = (CommonAbstActVo)actVo;
    }
}
