/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.dao;

import com.num.act.vo.AbstActVo;
import javolution.util.FastMap;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class ActDataDao {

    public boolean saveToDb(Integer userId, Integer actId, AbstActVo actVo) {

        FastMap<String, Object> saveMap = new FastMap<String, Object>();
        actVo.saveToDb(saveMap);



        return true;
    }

    public void loadFromDb(Integer userId, FastMap<Integer, Class<? extends AbstActVo>> actClass) {

        String sql = "select id from player where plyaer_id = :player_id";
        MapSqlParameterSource source = new MapSqlParameterSource();
//        source.addValue("player_id", playerId);
//        Player player = namedParameterJdbcTemplate.queryForObject(sql, source, new RowMapper<Player>() {
//            @Override
//            public Player mapRow(ResultSet rs, int i) throws SQLException {
//                return null;
//            }
//        });

    }
}
