package com.ozzot.userstore.dao.jdbc;

import com.ozzot.userstore.dao.UserDao;
import com.ozzot.userstore.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class JdbcUserDaoTest {

    private UserDao userDao;

    private JdbcTemplate jdbcTemplate;

    private EmbeddedDatabase embeddedDatabase;

    @Before
    public void before() {

        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();

        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        userDao = new JdbcUserDao(jdbcTemplate);
    }

    @After
    public void after() {
        embeddedDatabase.shutdown();
    }

    @Test
    public void getAll() {
        assertNotNull(userDao.getAll());
        assertEquals(4, userDao.getAll().size());
    }

    @Test
    public void getById() {
        assertEquals("name_1", userDao.getById(1).getName());
    }

    @Test
    public void add() {
        //prepare
        User user = new User(5, "name_5", "email_5", LocalDate.parse("2011-11-15"));

        assertEquals(4, userDao.getAll().size());

        //when
        userDao.add(user);

        //then
        assertEquals(5, userDao.getAll().size());
        assertEquals(user.getBirth(), userDao.getById(5).getBirth());
    }

    @Test
    public void update() {
        //prepare
        User user = new User(5, "name_5", "email_5", LocalDate.parse("2011-11-15"));

        assertNotEquals(user.getName(), userDao.getById(2).getName());

        //when
        userDao.update(user, 2);

        //then
        assertEquals(user.getName(), userDao.getById(2).getName());

    }

    @Test
    public void delete() {

        assertEquals(4, userDao.getAll().size());

        //when
        userDao.delete(1);

        //then
        assertEquals(3, userDao.getAll().size());

        int i = 2;
        for (User user : userDao.getAll()) {
            assertEquals(i, user.getId());
            i++;
        }

    }
}