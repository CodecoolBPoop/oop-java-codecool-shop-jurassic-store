package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.ShoppingCartSessionMap;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/shopping-cart"})
public class ShoppingCartController extends HttpServlet {

    private ShoppingCartSessionMap shoppingCartSessionMap = ShoppingCartSessionMap.getInstance();
    private ShoppingCartDaoMem shoppingCartDaoMem;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        shoppingCartDaoMem = shoppingCartSessionMap.getShoppingCartBySession(session);

        double sumPrice = Math.round(shoppingCartDaoMem.sumOfPrice()*100.0)/100.0;

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("shoppingCart",shoppingCartDaoMem.getAll());
        context.setVariable("sumPrice",sumPrice);
        engine.process("product/shoppingcart.html", context, resp.getWriter());
    }
}
