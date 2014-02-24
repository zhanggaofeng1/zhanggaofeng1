/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.dao;

import com.num.common.CdLv;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class TestDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<CdLv> showLvInfo(Integer level) {
        
        String sql = "select * from cd_lv";
        if (level != null && level > 0) {
            sql += " where player_lv = :player_lv";
        }
        
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("player_lv", level);
        
        List<CdLv> data = namedParameterJdbcTemplate.query(sql, source, new RowMapper<CdLv>(){
            @Override
            public CdLv mapRow(ResultSet rs, int i) throws SQLException {
                CdLv cdLv = new CdLv();
                cdLv.setPlayer_lv(rs.getInt("player_lv"));
                cdLv.setPlayer_exp(rs.getInt("player_experience"));
                cdLv.setBat_exp(rs.getInt("battle_experience"));
                return cdLv;
            }
        });
        
        return data;
    }
    
    public boolean deleteLvInfo(Integer level) {

        String sql = "delete from cd_lv where player_lv = :player_lv";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("player_lv", level);
        return 1 <= namedParameterJdbcTemplate.update(sql, source);
    }
    
    public boolean addLvInfo(CdLv cdLv) {
        String sql = "insert into cd_lv(player_lv, player_experence, bat_experence) values(:player_vl, :player_experence, :bat_experence)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("player_lv", cdLv.getPlayer_lv());
        source.addValue("player_experence", cdLv.getPlayer_exp());
        source.addValue("bat_experence", cdLv.getBat_exp());
        KeyHolder keyHolder = new GeneratedKeyHolder();// 适用于数据库自动产生ID
        int result = namedParameterJdbcTemplate.update(sql, source, keyHolder);
        return false;
    }
}
