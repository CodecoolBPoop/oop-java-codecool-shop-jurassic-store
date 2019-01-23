package com.codecool.shop.dao;

import com.codecool.shop.model.ShoppingCartElement;

import java.util.List;


public interface ShoppingCartDao {


    void addToList(ShoppingCartElement item);
    void removeFromList(ShoppingCartElement item);
    List<ShoppingCartElement> getAll();
}
