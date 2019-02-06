package com.codecool.shop.dao.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    void remove(String query, int id) {
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
