//package com.ozzot.userstore.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SpringJdbcConfig {
//
//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/testDB?autoReconnect=true&useSSL=false");
//        dataSource.setUsername("root");
//        dataSource.setPassword("1234567890QQQ");
//        return dataSource;
//    }
////
////    @Bean
////    public JdbcTemplate jdbcTemplate(){
////        return new JdbcTemplate();
////    }
//
//
//}
