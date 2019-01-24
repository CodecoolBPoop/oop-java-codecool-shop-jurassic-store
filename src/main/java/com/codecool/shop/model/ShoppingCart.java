package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {
    private double sum;

    ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();

    public List<ShoppingCartElement> getProductsInCart() {
        return shoppingCart.getAll();
    }

    public double sumOfPrice() {
        sum = 0;
        shoppingCart.getAll().forEach(item -> sum += Double.parseDouble(item.getProduct().getPrice()));
        return sum;
    }
}
