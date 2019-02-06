package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoMemTest {
    ProductCategoryDao productCategoryDao;
    ProductCategory herbivorous;



    @BeforeEach
    void setUp() {
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        herbivorous = new ProductCategory("Herbivorous", "Dinosaur", "Vegan");
    }

    @AfterEach
    void tearDown() {
        productCategoryDao = null;
        herbivorous = null;
    }

    @Test
    @DisplayName("Testing add: ")
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
        assertTrue(productCategoryDao.getAll().contains(herbivorous));
        assertNull(productCategoryDao.find(0));
    }

    @Test
    void remove() {
        productCategoryDao.add(herbivorous);
        assertTrue(productCategoryDao.getAll().contains(herbivorous));
        productCategoryDao.remove(1);
        assertFalse(productCategoryDao.getAll().contains(herbivorous));
    }

}