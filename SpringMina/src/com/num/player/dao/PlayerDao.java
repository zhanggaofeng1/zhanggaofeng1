/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.dao;

import com.num.common.daoUtils.NamedParameterDaoSupports;
import com.num.player.vo.Player;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class PlayerDao extends NamedParameterDaoSupports {

    private static final Logger log = LoggerFactory.getLogger(PlayerDao.class);
    private static final short version = 1;

    public boolean savePlayer(Player player) {

        String sql = "insert blob_tab(flag, data) values(:flag, :data)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("flag", 301);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int count = save(buf);
        ByteArrayInputStream data = new ByteArrayInputStream(buf.array(), 0, count);
        source.addValue("data", data);
        return 1 == this.insertWithBlobData(sql, source);
    }

    public boolean loadPlayer() {
        
        String sql = "select id, flag, data from blob_tab";
        MapSqlParameterSource source = new MapSqlParameterSource();
        namedParameterJdbcTemplate.query(sql, source, new RowMapper<Integer>() {
            
            @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {

                int id = rs.getInt("id");
                int flag = rs.getInt("flag");
                System.out.println("id = " + id + "  ,   flag = " + flag);
                Blob blob = rs.getBlob("data");
                byte[] arr = blob.getBytes(1, (int)blob.length());
                read(ByteBuffer.wrap(arr));
                return 9;
            }
        });

        return true;
    }

    private void read(ByteBuffer buf) {
    
        int v = buf.getShort();
        int da = buf.getInt();
        int strLen = buf.getInt();
        char[] arr = new char[strLen];
        for (int i = 0; i < strLen; i++) {
            arr[i] = buf.getChar();
        }
        String str = new String(arr);
        System.out.println("version = " + v + " , num = " + da + " ,  str = " + str);
    }
    
    private int save(ByteBuffer buf) {
        
        buf.putShort(version);
        buf.putInt(100);
        String str = "I LOVE YOU";
        buf.putInt(str.length());
        for (int i = 0; i < str.length(); i++) {
            buf.putChar(str.charAt(i));
        }
        return buf.position();
    }
}
