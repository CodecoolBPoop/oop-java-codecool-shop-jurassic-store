package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoMemTest {
    SupplierDao supplierDao;
    Supplier supplier;

    @BeforeEach
    void setUp() {
        supplierDao = SupplierDaoMem.getInstance();
        supplier = new Supplier("Monsanto", "The Monsanto Company was an American agrochemical and agricultural biotechnology corporation.");
    }

    @AfterEach
    void tearDown() {
        supplierDao.getAll().removeAll(supplierDao.getAll());
        supplierDao = null;
        supplier = null;
    }

    @Test
    @DisplayName("Testing add function")
    void testingAddNewSupplier() {
        supplierDao.add(supplier);
        assertTrue(supplierDao.getAll().contains(supplier));
    }

    @Test
    @DisplayName("Testing find function")
    void testingFindSupplierWithID() {
        supplierDao.add(supplier);
        assertEquals(supplier, supplierDao.find(1));
    }

    @Test
    @DisplayName("Testing remove function")
    void testingRemoveSupplierWithId() {
        supplierDao.add(supplier);
        supplierDao.remove(1);
        assertFalse(supplierDao.getAll().contains(supplier));
    }

}