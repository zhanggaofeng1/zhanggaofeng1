/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.dao;

import com.alibaba.fastjson.JSON;
import com.num.act.vo.CommonActVo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javolution.util.FastMap;
import org.slf4j.Logger;
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
public class PlayerActDao {

    private static final Logger log = LoggerFactory.getLogger(PlayerActDao.class);
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public boolean savePlayerActInfo(Integer userId, CommonActVo actVo) {

        String sql;
        MapSqlParameterSource source = new MapSqlParameterSource();
        if (actVo.getId() <= 0) {
            sql = "insert act_info(u_id, act_id, dt, join_ct, json) values(:u_id, :act_id, :dt, :join_ct, :json)";
            source.addValue("u_id", userId);
            source.addValue("act_id", actVo.getActId());
            source.addValue("dt", actVo.getDate());
            source.addValue("join_ct", actVo.getJoinCt());
            source.addValue("json", actVo.getParamJson());
        } else {
            sql = "update act_info set dt = :dt, join_ct = :join_ct, json = :json where id = :id";
            source.addValue("dt", actVo.getDate());
            source.addValue("join_ct", actVo.getJoinCt());
            source.addValue("json", actVo.getParamJson());
            source.addValue("id", actVo.getId());
        }
        return 1 == namedParameterJdbcTemplate.update(sql, source);
    }

    public List<CommonActVo> loadPlayerActInfo(final Integer userId, final FastMap<Integer, Class<? extends CommonActVo>> actClass) {

        String sql = "select id, u_id, act_id, dt, join_ct, json from act_table where u_id = :u_id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("u_id", userId);
        return namedParameterJdbcTemplate.query(sql, source, new RowMapper<CommonActVo>() {
            @Override
            public CommonActVo mapRow(ResultSet rs, int i) throws SQLException {

                int actId = rs.getInt("act_id");
                int id = rs.getInt("id");
                String dt = rs.getString("dt");
                int joinCt = rs.getInt("join_ct");
                String json = rs.getString("json");

                Class<? extends CommonActVo> clazz = actClass.get(actId);
                if (clazz == null) {
                    log.error("没有找到数据库活动id = " + actId + " 对象的逻辑对象！！！");
                    return null;
                }

                try {
                    CommonActVo actVo = clazz.newInstance();
                    return actVo.init_data(id, actId, dt, joinCt, json);
                } catch (InstantiationException | IllegalAccessException ex) {
                    log.error(ex.getMessage());
                    return null;
                }
            }
        });
    }
}
