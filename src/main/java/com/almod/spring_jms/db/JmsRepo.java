package com.almod.spring_jms.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import javax.jms.TextMessage;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class JmsRepo {
    private final Logger LOG = LoggerFactory.getLogger(JmsRepo.class);

    private final JdbcTemplate jdbcTemplate;

    public JmsRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void writeInDB(TextMessage mg) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sqlMessageDB = "INSERT INTO message(text) VALUES(?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlMessageDB, Statement.RETURN_GENERATED_KEYS);
            try {
                ps.setString(1, mg.getText());
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
            return ps;
        }, keyHolder);

        String sqlHeadersDB = "INSERT INTO headers(id, head) VALUES(?, ?)";
        try {
            jdbcTemplate.update(sqlHeadersDB, keyHolder.getKey().longValue(), mg.getJMSType());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    public void showDataDB() {
        LOG.info("Show result in database");
        String sql = "SELECT mg.text, hs.head" +
                " FROM message mg" +
                " INNER JOIN headers hs" +
                " ON mg.id = hs.id;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()) {
            LOG.info(rowSet.getString(1) + "  " + rowSet.getString(2));
        }
    }
}

