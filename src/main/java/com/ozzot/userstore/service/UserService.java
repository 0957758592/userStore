package com.ozzot.userstore.service;

import com.ozzot.userstore.dao.UserDao;
import com.ozzot.userstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserDao userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public void addUser(User user) {
        userDao.add(user);
    }

    public void update(User user, int id) {
        userDao.update(user, id);
    }

    public void delete(int id) {
        userDao.delete(id);
    }
}
