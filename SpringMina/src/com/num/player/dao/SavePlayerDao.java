/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.dao;

import com.num.player.vo.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class SavePlayerDao {

     @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
     
    public boolean savePlayer(Player player) {

        String sql = "update player set player_name = :player_name, player_lv = :player_lv, player_vip = :player_vip set id = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("player_name", player.getPlayerName());
        source.addValue("player_lv", player.getPlayerLv());
        source.addValue("player_vip", player.getPlayerVip());
        source.addValue("id", player.getId());
        return 1 == namedParameterJdbcTemplate.update(sql, source);
    }
}
