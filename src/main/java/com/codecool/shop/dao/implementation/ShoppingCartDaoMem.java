package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.ShoppingCartElement;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {
    private static ShoppingCartDaoMem instance = null;
    private int numberOfElements;
    private double sum;

    private List<ShoppingCartElement> productsInCart = new ArrayList<>();

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public double sumOfPrice() {
        sum = 0;
        ShoppingCartDaoMem.getInstance().getAll().forEach(item -> sum += item.getProduct().getPriceDouble() * item.getQuantity());
        return sum;
    }

    @Override
    public void addToList(ShoppingCartElement item) {
        productsInCart.add(item);
    }

    @Override
    public void removeFromList(ShoppingCartElement item) {
        productsInCart.remove(item);
    }

    @Override
    public void removeAll() {
        instance = null;
        getInstance();
    }

    @Override
    public int numberOfElements() {
        numberOfElements = 0;
        productsInCart.forEach(shoppingCartElement -> numberOfElements += shoppingCartElement.getQuantity());
        return numberOfElements;
    }

    @Override
    public List<ShoppingCartElement> getAll() {
        return productsInCart;
    }
}
