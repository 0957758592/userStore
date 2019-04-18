package com.ozzot.userstore.dao.jdbc;

import com.ozzot.userstore.dao.user.UserDao;
import com.ozzot.userstore.entity.User;
import com.ozzot.userstore.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.ozzot.userstore.dao.jdbc.JdbcConstants.*;

@Repository
public class JdbcUserDao implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        users.addAll(jdbcTemplate.query(GET_ALL, new UserMapper()));
        return users;
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(GET_BY_ID, new UserMapper(), id);
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update(ADD_USER, user.getName(),  user.getBirth(), user.getEmail());
    }

    @Override
    public void update(User user, int id) {
        jdbcTemplate.update(UPDATE, user.getName(), user.getBirth(), user.getEmail(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE, id);
    }

}
