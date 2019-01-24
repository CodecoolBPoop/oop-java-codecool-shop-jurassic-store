package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCartElement;
import com.google.gson.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart-api"})
public class CartApi extends HttpServlet {
    private Boolean prodFound;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        int id = Integer.parseInt(req.getParameter("prodId"));
        String action = req.getParameter("action");
        HashMap map = new HashMap();

        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Iterator cartIter = shoppingCart.getAll().iterator();
        Iterator shopIter = productDataStore.getAll().iterator();

        if (action.equals("removeAll")) {
            shoppingCart.removeAll();
        } else {
            this.prodFound = false;
            while (shopIter.hasNext() && !prodFound) {
                Product prod = (Product) shopIter.next();
                if (prod.getId() == id) {
                    while (cartIter.hasNext()) {
                        ShoppingCartElement product = (ShoppingCartElement) cartIter.next();
                        if (product.getProduct().equals(prod)) {
                            if (action.equals("add")) {
                                this.prodFound = true;
                                handleQuantity(product, "plus", map);
                            } else {
                                this.prodFound = true;
                                if (product.getQuantity() > 1) {
                                    handleQuantity(product, "min", map);
                                } else {
                                    cartIter.remove();
                                }
                            }
                            map.put("productId", product.getProduct().getId());
                        }
                    }

                    if (!prodFound) {
                        shoppingCart.addToList(new ShoppingCartElement(prod, 1));
                    }
                }
            }

        double sumPrice = shoppingCart.sumOfPrice();
        map.put("sumPrice",sumPrice);
        writeResponse(resp, map);
        }
    }

    private void writeResponse(HttpServletResponse resp, HashMap map) throws IOException {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    private void handleQuantity(ShoppingCartElement product, String act, HashMap map) {
        if(act.equals("plus")) {
            product.setQuantity(product.getQuantity() + 1);
        } else {
            product.setQuantity(product.getQuantity() - 1);
        }
        map.put("prodQuantity", product.getQuantity());
    }
}
