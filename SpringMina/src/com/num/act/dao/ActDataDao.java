/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.dao;

import com.alibaba.fastjson.JSON;
import com.num.act.vo.AbstActVo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javolution.util.FastMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class ActDataDao {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ActDataDao.class);
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public boolean saveToDb(Integer userId, AbstActVo actVo) {

        String act_data = JSON.toJSONString(actVo);
        MapSqlParameterSource source = new MapSqlParameterSource();
        String sql = null;
        if (actVo.curDbId() <= 0) {
            sql = "insert act_table(user_id, act_id, act_data) values(:user_id, :act_id, :act_data)";
            source.addValue("user_id", userId);
            source.addValue("act_id", actVo.curActId());
            source.addValue("act_data", act_data);
        } else {
            sql = "update act_table set act_data = :act_data where id = :id";
            source.addValue("act_data", act_data);
            source.addValue("id", actVo.curDbId());
        }
        return 1 == namedParameterJdbcTemplate.update(sql, source);
    }

    public List<AbstActVo> loadFromDb(final Integer userId, final FastMap<Integer, Class<? extends AbstActVo>> actClass) {

        String sql = "select id, user_id, act_id, act_data from act_table where user_id = :user_id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("user_id", userId);
        return namedParameterJdbcTemplate.query(sql, source, new RowMapper<AbstActVo>() {
            @Override
            public AbstActVo mapRow(ResultSet rs, int i) throws SQLException {

                int id = rs.getInt("id");

                int actId = rs.getInt("act_id");
                Class<? extends AbstActVo> clazz = actClass.get(actId);
                if (clazz == null) {
                    log.error("没有找到数据库活动id = " + actId + " 对象的逻辑对象！！！");
                    return null;
                }

                String actData = rs.getString("act_data");
                AbstActVo actVo = null;
                if (actData == null || actData.isEmpty()) {
                    try {
                        actVo = clazz.newInstance();
                    } catch (InstantiationException | IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    actVo = JSON.parseObject(actData, clazz);
                }
                return actVo.fromDbInit(id, actId);
            }
        });
    }
}
