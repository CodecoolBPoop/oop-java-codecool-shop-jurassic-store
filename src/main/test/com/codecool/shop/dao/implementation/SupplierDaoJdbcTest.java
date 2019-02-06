package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJdbcTest {
    SupplierDao supplierDao;
    Supplier supplier;

    @BeforeEach
    void setUp() {
        supplierDao = SupplierDaoJdbc.getInstance();
        supplier = new Supplier("Monsanto", "The Monsanto Company was an American agrochemical and agricultural biotechnology corporation.");
    }

    @AfterEach
    void tearDown() {
        ((SupplierDaoJdbc) supplierDao).removeAll();
        supplierDao = null;
        supplier = null;
    }

    @Test
    @DisplayName("Testing add function")
    void testAddSupplier() {
        supplierDao.add(supplier);
        assertTrue(supplierDao.getAll().contains(supplier));
    }

    @Test
    @DisplayName("Testing find function")
    void testFindSupplier() {
        supplierDao.add(supplier);
        assertEquals(supplier, supplierDao.find(1));
    }

    @Test
    @DisplayName("Testing remove function")
    void testRemoveSupplier() {
        supplierDao.add(supplier);
        supplierDao.remove(1);
        assertFalse(supplierDao.getAll().contains(supplier));
    }

    @Test
    @DisplayName("Testing Illegal Arg. Exception in remove")
    void testIllegalArgRemove() {
        supplierDao.add(supplier);
        assertThrows(IllegalArgumentException.class, ()-> supplierDao.remove(-1));
    }
}