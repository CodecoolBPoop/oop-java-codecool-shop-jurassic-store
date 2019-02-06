package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoJdbs extends DaoJdbc implements ProductDao {

    SupplierDaoJdbc supplierDaoJdbc = SupplierDaoJdbc.getInstance();
    ProductCategoryDaoJdbc productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products(supplier_id, category_id, name, description, price, currency) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getSupplier().getId());
            preparedStatement.setInt(2, product.getProductCategory().getId());
            preparedStatement.setString(3,product.getName());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setFloat(5, product.getDefaultPrice());
            preparedStatement.setString(6, product.getDefaultCurrency().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM products WHERE id = ?";
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            if(resultSet.next()){
                return productFactory(resultSet);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM products WHERE id = ?";
        remove(query, id);
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products";
        List<Product> result = new ArrayList<>();
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            while(resultSet.next()){
                result.add(productFactory(resultSet));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM products WHERE supplier_id = ?";
        int supplierId = supplier.getId();
        return getProducts(query, supplierId);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM products WHERE category_id = ?";
        int categoryId = productCategory.getId();
        return getProducts(query, categoryId);
    }

    @Override
    public List<Product> getBy(List<Supplier> suppliers) {
        List<Product> result = new ArrayList<>();
        String query = "SELECT * FROM products WHERE supplier_id = ?";
        for (Supplier supplier : suppliers) {
            int supplierId = supplier.getId();
            result.addAll(getProducts(query, supplierId));
        }
        return result;
    }

    @Override
    public List<Product> getBy(ArrayList<ProductCategory> productCategories) {
        List<Product> result = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = ?";
        for (ProductCategory productCategory : productCategories) {
            int  categoryId = productCategory.getId();
            result.addAll(getProducts(query, categoryId));
        }
        return result;
    }

    @Override
    public List<Product> getBy(List<Supplier> suppliers, List<ProductCategory> categories) {
        List<Product> sortedDataBySupplier = getBy(suppliers);
        return sortByCategory(sortedDataBySupplier, categories);

    }

    private Product productFactory(ResultSet resultSet) throws SQLException {
        Product product = new Product(
                resultSet.getString("name"),
                resultSet.getFloat("price"),
                resultSet.getString("currency"),
                resultSet.getString("description"),
                productCategoryDaoJdbc.find(resultSet.getInt("category_id")),
                supplierDaoJdbc.find(resultSet.getInt("supplier_id")));
        product.setId(resultSet.getInt("id"));
        return product;
    }

    private List<Product> sortByCategory(List<Product> data, List<ProductCategory> categories) {
        List<Product> sortedData = new ArrayList<>();
        for (ProductCategory category : categories) {
            sortedData.addAll(data.stream().filter(t -> t.getProductCategory().equals(category)).collect(Collectors.toList()));
        }
        return sortedData;
    }

    private List<Product> getProducts(String query, int id) {
        List<Product> result = new ArrayList<>();
        try (Connection connection = getConnection()
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            while(resultSet.next()){
                result.add(productFactory(resultSet));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
