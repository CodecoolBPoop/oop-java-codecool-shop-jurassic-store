package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJdbcTest {
    ProductCategoryDao productCategoryDao;
    ProductCategory herbivorous;

    @BeforeEach
    void setUp() {
        productCategoryDao = ProductCategoryDaoJdbc.getInstance();
        herbivorous = new ProductCategory("Herbivorous", "Dinosaur", "Vegan");
    }

    @AfterEach
    void tearDown(){
        ((ProductCategoryDaoJdbc) productCategoryDao).removeAll();
        productCategoryDao = null;
        herbivorous = null;
    }

    @Test
    void add() {
        productCategoryDao.add(herbivorous);
        assertTrue(productCategoryDao.getAll().contains(herbivorous));
    }

    @Test
    void find() {
        productCategoryDao.add(herbivorous);
        assertEquals(herbivorous, productCategoryDao.find(1));
    }

    @Test
    void findNot() {
        productCategoryDao.add(herbivorous);
        assertNull(productCategoryDao.find(0));
    }

    @Test
    void remove() {
        productCategoryDao.add(herbivorous);
        productCategoryDao.remove(1);
        assertFalse(productCategoryDao.getAll().contains(herbivorous));
    }
}