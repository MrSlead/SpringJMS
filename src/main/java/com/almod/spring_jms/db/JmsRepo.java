package com.almod.spring_jms.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JmsRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void test() {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * from message");
        while(rowSet.next()) {
            System.out.println(rowSet.getString(1) + "  " + rowSet.getString(2));
        }

    }
}

