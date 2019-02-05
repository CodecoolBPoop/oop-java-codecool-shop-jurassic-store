package com.codecool.shop.dao.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class DaoJdbc {

    private static final String DATABASE = System.getenv("DATABASE");
    private static final String DB_USER = System.getenv("USER");
    private static final String DB_PASSWORD = System.getenv("PASSWORD");

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }
}
