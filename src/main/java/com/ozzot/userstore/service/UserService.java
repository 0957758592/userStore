package com.ozzot.userstore.service;

import com.ozzot.userstore.dao.jdbc.JdbcUserDao;
import com.ozzot.userstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    @Autowired
    private JdbcUserDao jdbcUserDao;

    public List<User> getAll() {
        return jdbcUserDao.getAll();
    }

    public User getById(int id) {
        return jdbcUserDao.getById(id);
    }

    public void addUser(User user) {
        jdbcUserDao.add(user);
    }

    public void update(User user, int id) {
        jdbcUserDao.update(user, id);
    }

    public void delete(int id) {
        jdbcUserDao.delete(id);
    }
}
