package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc extends DaoJdbc implements ProductCategoryDao {
    private static ProductCategoryDaoJdbc instance = null;

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category){
        String query = "INSERT INTO product_cat(description,department,name) VALUES(?,?,?)";
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,category.getDescription());
            System.out.println(category.getDescription());
            preparedStatement.setString(2,category.getDepartment());
            preparedStatement.setString(3,category.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM product_cat WHERE id = ?";
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                ProductCategory productCategory = new ProductCategory(
                                                                resultSet.getString("name"),
                                                                resultSet.getString("department"),
                                                                resultSet.getString("description")
                                    );
                productCategory.setId(resultSet.getInt("id"));
                return productCategory;
            }
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product_cat WHERE id = ?";
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

    public void removeAll() {
        String query = "DELETE FROM product_cat";
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM product_cat";
        List<ProductCategory> result = new ArrayList<>();
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ProductCategory productCategory = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
                );
                productCategory.setId(resultSet.getInt("id"));
                result.add(productCategory);
            }
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
