package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCartElement;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart-api"})
public class CartApi extends HttpServlet {
    private Boolean prodFound;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*JsonParser parser = new JsonParser();
        String request = req.getReader().readLine();
        JsonObject jsonReq = (JsonObject) parser.parse(request);
        Integer id = Integer.parseInt(jsonReq.get("prodId").toString().replace("\"", ""));
        String action = jsonReq.get("action").toString().replace("\"", "");*/
        resp.setHeader("Access-Control-Allow-Origin", "*");
        String action = req.getParameter("action");
        Integer id = Integer.valueOf(req.getParameter("prodId"));
        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();

        ProductDao productDataStore = ProductDaoMem.getInstance();
        for (Product prod: productDataStore.getAll()
             ) {
            this.prodFound = false;
            if(prod.getId()==id) {
                if(!shoppingCart.getAll().isEmpty()) {
                    shoppingCart.getAll().forEach(product -> {
                        if (product.getProduct().equals(prod)) {
                            if(action.equals("add")) {
                                this.prodFound = true;
                                product.setQuantity(product.getQuantity() + 1);
                            } else {
                                if(product.getQuantity() > 1) {
                                    product.setQuantity(product.getQuantity() - 1);
                                } else {
                                    shoppingCart.getAll().remove(product);
                                }
                            }
                        }
                    });
                }

                if(!prodFound) {
                    shoppingCart.addToList(new ShoppingCartElement(prod, 1));
                }
            }
        }
    }
}
