/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.common.daoUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;

/**
 *
 * @author Administrator
 */
public abstract class NamedParameterDaoSupports {

    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    protected int insertWithBlobData(String sql, MapSqlParameterSource map) {
        return executeWithBlobData(sql, map);
    }

    protected int updateWithBlobData(String sql, MapSqlParameterSource map) {
        return executeWithBlobData(sql, map);
    }

    protected int executeWithBlobData(final String sql, MapSqlParameterSource map) {
        
        ParsedSql parseSql = NamedParameterUtils.parseSqlStatement(sql);
        final Object[] values = NamedParameterUtils.buildValueArray(parseSql, map, null);
        namedParameterJdbcTemplate.execute(sql, map, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                int valLen = values.length;
                for (int i = 0; i < valLen; i++) {
                    if (values[i] instanceof ByteArrayInputStream) {
                        ByteArrayInputStream data = (ByteArrayInputStream) values[i];
                        ps.setBinaryStream(i + 1, data, data.available());
                    }
                }
                return ps.executeUpdate();
            }
        });
        return 0;
    }
}
