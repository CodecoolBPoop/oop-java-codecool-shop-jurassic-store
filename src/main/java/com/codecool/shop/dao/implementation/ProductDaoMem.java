package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        product.setId(data.size() + 1);
        data.add(product);
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

    @Override
    public  List<Product> getBy(List<Supplier> suppliers) {
        List<Product> sortedData = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            sortedData.addAll(data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList()));
        }
        return sortedData;
    }

    @Override
    public List<Product> getBy(ArrayList<ProductCategory> categories) {
        return sortByCategory(data, categories);
    }

    @Override
    public List<Product> getBy(List<Supplier> suppliers, List<ProductCategory> categories) {
        List<Product> sortedDataBySupplier = getBy(suppliers);
        return sortByCategory(sortedDataBySupplier, categories);
    }

    private List<Product> sortByCategory(List<Product> data, List<ProductCategory> categories) {
        List<Product> sortedData = new ArrayList<>();
        for (ProductCategory category : categories) {
            sortedData.addAll(data.stream().filter(t -> t.getProductCategory().equals(category)).collect(Collectors.toList()));
        }
        return sortedData;
    }
}
