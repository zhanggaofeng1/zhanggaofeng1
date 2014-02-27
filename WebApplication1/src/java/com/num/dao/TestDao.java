/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.dao;

import com.num.common.Student;
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

    public List<Student> showLvInfo() {

        String sql = "select * from student";
        MapSqlParameterSource source = new MapSqlParameterSource();
        List<Student> data = namedParameterJdbcTemplate.query(sql, source, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int i) throws SQLException {
                Student stu = new Student();
                stu.setId(rs.getInt("id"));
                stu.setStuId(rs.getInt("stu_id"));
                stu.setStuName("stu_name");
                stu.setStuTel("stu_tel");
                stu.setTeaId("tea_id");
                return stu;
            }
        });
        return data;
    }

    public boolean insertOper() {

        String sql = "insert into student(stu_id, stu_name, stu_tel, tea_id) vlaues(:stu_id, :stu_name, :stu_tel, :tea_id)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("stu_id", 100);
        source.addValue("stu_name", "ooo");
        source.addValue("stu_tel", "137181");
        source.addValue("tea_id", 200);
        KeyHolder key = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(sql, source, key);
        return result == 1;
    }
}
