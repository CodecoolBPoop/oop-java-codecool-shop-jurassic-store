package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ShoppingCartElement;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartDaoMemTest {

    private ShoppingCartDao shoppingCartDao = new ShoppingCartDaoMem();
    private Supplier bayer = new Supplier("Bayer", "Bayer AG is a German multinational pharmaceutical and life sciences company and one of the largest pharmaceutical companies in the world.");
    private ProductCategory carnivorous = new ProductCategory("Carnivorous", "Dinosaur", "Not vegan");
    private Product product = new Product("T-rex (Bayer, carnivorous)", 1000, "USD", " A genus of coelurosaurian theropod dinosaur. The species Tyrannosaurus rex (rex meaning \"king\" in Latin), is one of the most well-represented of the large theropods.", carnivorous, bayer);
    private ShoppingCartElement shoppingCartElement = new ShoppingCartElement(product,10);


    @BeforeEach
    void before(){
        shoppingCartDao.removeAll();
    }

    @Test
    void sumOfPrice() {
        shoppingCartDao.addToList(shoppingCartElement);
        assertTrue(shoppingCartDao.getAll().contains(shoppingCartElement),"ADD IS NOT WORKING PROPERLY");
        assertEquals(10000,shoppingCartDao.sumOfPrice(),"SUM IS NOT WORKING PROPERLY");
    }

    @Test
    void addToList() {
        //do
        shoppingCartDao.addToList(shoppingCartElement);
        //assert
        assertTrue(shoppingCartDao.getAll().contains(shoppingCartElement));
    }

    @Test
    void removeFromList() {
        //init
        shoppingCartDao.addToList(shoppingCartElement);
        assertTrue(shoppingCartDao.getAll().contains(shoppingCartElement),"ADD NOT WORKING PROPERLY");
        //do
        shoppingCartDao.removeFromList(shoppingCartElement);
        //assert
        assertFalse(shoppingCartDao.getAll().contains(shoppingCartElement));
    }

    @Test
    void numberOfElements() {
        //init
        shoppingCartDao.addToList(shoppingCartElement);
        assertTrue(shoppingCartDao.getAll().contains(shoppingCartElement),"ADD NOT WORKING PROPERLY");
        //do,assert
        assertEquals(10,shoppingCartDao.numberOfElements());
    }
}