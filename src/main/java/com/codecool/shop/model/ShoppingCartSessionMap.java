package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoppingCartSessionMap {
    private static ShoppingCartSessionMap instance = new ShoppingCartSessionMap();
    private Map<HttpSession, ShoppingCartDaoMem> shoppingCarts = new HashMap<HttpSession, ShoppingCartDaoMem>();
    private double sum;

    public static ShoppingCartSessionMap getInstance() {
        if (instance == null) {
            instance = new ShoppingCartSessionMap();
        }
        return instance;
    }

    public void addToList(HttpSession session, ShoppingCartDaoMem shoppingCart){
        shoppingCarts.put(session, shoppingCart);
    }

    public void removeFromList(HttpSession session){
        shoppingCarts.remove(session);
    }

    public ShoppingCartDaoMem getShoppingCartBySession(HttpSession session){
        if (isSessionAlreadyPresent(session)) return shoppingCarts.get(session);
        else {
            ShoppingCartDaoMem shoppingCartDaoMem = new ShoppingCartDaoMem();
            addToList(session, shoppingCartDaoMem);
            return shoppingCartDaoMem;
        }
    }

    public boolean isSessionAlreadyPresent(HttpSession session){
        if (shoppingCarts.get(session) == null) return false;
        return true;
    }

    //ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();

    //public List<ShoppingCartElement> getProductsInCart() {
        //return shoppingCart.getAll();
    //}

    //public double sumOfPrice() {
    //    sum = 0;
    //    shoppingCart.getAll().forEach(item -> sum += Double.parseDouble(item.getProduct().getPrice()));
    //    return sum;
    //}
}
