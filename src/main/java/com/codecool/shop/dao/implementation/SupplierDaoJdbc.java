package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc extends DaoJdbc implements SupplierDao {
    private static SupplierDaoJdbc instance = null;

    public static SupplierDaoJdbc getInstance() {
        if(instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO suppliers(name, description) VALUES(?, ?)";
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,supplier.getName());
            preparedStatement.setString(2,supplier.getDescription());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM suppliers WHERE id = ?";
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                supplier.setId(resultSet.getInt("id"));
                return supplier;
            }
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM suppliers WHERE id = ?";
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

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM suppliers";
        List<Supplier> result = new ArrayList<>();
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                supplier.setId(resultSet.getInt("id"));
                result.add(supplier);
            }
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void removeAll() {
        String query = "DELETE FROM suppliers";
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
