package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCartElement;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart-api"})
public class CartApi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonParser parser = new JsonParser();
        String request = req.getReader().readLine();
        JsonObject jobj = (JsonObject) parser.parse(request);
        Integer id = Integer.parseInt(jobj.get("prodId").toString().replace("\"", ""));
        String action = jobj.get("action").toString().replace("\"", "");
        System.out.println(id);
        System.out.println(action);

        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();

        ProductDao productDataStore = ProductDaoMem.getInstance();
        for (Product prod: productDataStore.getAll()
             ) {
            if(prod.getId()==id) {
               shoppingCart.addToList(new ShoppingCartElement(prod, 1));
            }
        };

    }
}
