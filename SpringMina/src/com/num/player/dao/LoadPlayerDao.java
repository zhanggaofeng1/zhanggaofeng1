/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.dao;

import com.num.player.vo.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
public class LoadPlayerDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Player loadPlayer(final int playerId) {
        String sql = "select * from player where plyaer_id = :player_id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("player_id", playerId);
        Player player = namedParameterJdbcTemplate.queryForObject(sql, source, new RowMapper<Player>() {
            @Override
            public Player mapRow(ResultSet rs, int i) throws SQLException {
                return null;
            }
        });
        return player;
    }
}
