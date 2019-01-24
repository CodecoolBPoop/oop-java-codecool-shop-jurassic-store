package com.codecool.shop.dao;

import com.codecool.shop.model.ShoppingCartElement;

import java.util.List;


public interface ShoppingCartDao {

    double sumOfPrice();
    void addToList(ShoppingCartElement item);
    void removeFromList(ShoppingCartElement item);
    void removeAll();
    int numberOfElements();
    List<ShoppingCartElement> getAll();
}
