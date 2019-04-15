package com.ozzot.userstore.dao.jdbc;

class JdbcConstants {
    static final String GET_ALL = "SELECT * from users;";
    static final String GET_BY_ID = "SELECT userId,  firstName, lastName, birth, email, phoneNumber from users where userId=?;";
    static final String ADD_USER = "INSERT INTO users (firstName, lastName, birth, email, phoneNumber) values (?,?,?,?,?);";
    static final String DELETE = "DELETE from users where userId=?;";
    static final String UPDATE = "UPDATE users SET firstName=?, lastName=?, birth=?, email=?, phoneNumber=? WHERE userId=?;";
}
