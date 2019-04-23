package com.ozzot.userstore.dao.jdbc;

import com.ozzot.userstore.dao.UserDao;
import com.ozzot.userstore.entity.User;
import com.ozzot.userstore.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserDao implements UserDao {
    private static final String GET_ALL = "SELECT * from users;";
    private static final String GET_BY_ID = "SELECT id, name, birth, email from users WHERE id=?;";
    private static final String ADD_USER = "INSERT INTO users (name, birth, email) values (?,?,?);";
    private static final String DELETE = "DELETE from users where id=?;";
    private static final String UPDATE = "UPDATE users SET name=?, birth=?, email=? WHERE id=?;";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(GET_ALL, new UserMapper());
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(GET_BY_ID, new UserMapper(), id);
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update(ADD_USER, user.getName(), user.getBirth(), user.getEmail());
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
