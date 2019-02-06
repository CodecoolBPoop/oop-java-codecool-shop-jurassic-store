package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest {

    private ProductDao productDao;
    private Product product;
    private Supplier supplier;
    private ProductCategory productCategory;


    @BeforeEach
    void setUp() {
        productDao = ProductDaoJdbc.getInstance();
        productCategory = new ProductCategory("Carnivorous", "Dinosaur", "Not vegan");
        supplier = new Supplier("Bayer", "Bayer AG is a German multinational pharmaceutical and life sciences company and one of the largest pharmaceutical companies in the world.");
        product = new Product("T-rex (Bayer, carnivorous)", 9, "USD", " A genus of coelurosaurian theropod dinosaur.", productCategory, supplier);
    }

    @AfterEach
    void tearDown() {
        productDao.getAll().removeAll(productDao.getAll());
        productCategory = null;
        supplier = null;
        product = null;
    }

    @Test
    void testAdd() {
        productDao.add(product);
        assertTrue(productDao.getAll().contains(product));
    }

    @Test
    void testFind() {
        productDao.add(product);
        assertEquals(product, productDao.find(product.getId()));
    }

    @Test
    void testIfNotFind() {
        productDao.add(product);
        assertNull(productDao.find(0));
    }

    @Test
    void remove() {
        productDao.add(product);
        productDao.remove(product.getId());
        assertFalse(productDao.getAll().contains(product));
    }
}