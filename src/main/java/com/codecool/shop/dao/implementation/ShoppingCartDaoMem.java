package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.ShoppingCartElement;
import com.codecool.shop.model.ShoppingCartSessionMap;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {
    private int numberOfElements;
    private double sum;

    private List<ShoppingCartElement> productsInCart = new ArrayList<>();
    //TODO


    @Override
    public double sumOfPrice() {
        sum = 0;
        getAll().forEach(item -> sum += item.getProduct().getPriceFloat() * item.getQuantity());
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
        productsInCart.clear();
        numberOfElements = 0;
        sum = 0;
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
