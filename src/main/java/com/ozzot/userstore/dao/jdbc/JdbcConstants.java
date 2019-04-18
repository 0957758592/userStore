package com.ozzot.userstore.dao.jdbc;

class JdbcConstants {
    static final String GET_ALL = "SELECT * from users;";
    static final String GET_BY_ID = "SELECT id, name, birth, email from users WHERE id=?;";
    static final String ADD_USER = "INSERT INTO users (name, birth, email) values (?,?,?);";
    static final String DELETE = "DELETE from users where id=?;";
    static final String UPDATE = "UPDATE users SET name=?, birth=?, email=? WHERE id=?;";
}
