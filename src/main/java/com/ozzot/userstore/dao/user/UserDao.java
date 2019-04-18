package com.ozzot.userstore.dao.user;

import com.ozzot.userstore.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getById(int id);

    void add(User user);

    void update(User user, int id);

    void delete(int id);

}
