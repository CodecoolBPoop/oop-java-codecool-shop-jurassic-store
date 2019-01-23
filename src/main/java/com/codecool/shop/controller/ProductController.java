package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    private ProductDao productDataStore = ProductDaoMem.getInstance();
    private ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

//        context.setVariables(params);
        context.setVariable("recipient", "World");
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        if (req.getParameter("category") == null && req.getParameter("supplier") == null) {
            context.setVariable("category", productCategoryDataStore.find(1));
            context.setVariable("products", productDataStore.getAll());
        } else if (req.getParameter("category")!= null && req.getParameter("supplier") == null){
            filterByCategory(context, req);
        } else if (req.getParameter("category") == null && req.getParameter("supplier") != null) {
            filterBySupplier(context, req);
        }
        engine.process("product/index.html", context, resp.getWriter());

    }

    private void filterByCategory(WebContext context, HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("category"));
        context.setVariable("category", productCategoryDataStore.find(id));
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(id)));
    }

    private void filterBySupplier(WebContext context, HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("supplier"));
        context.setVariable("category", supplierDataStore.find(id));
        context.setVariable("products", productDataStore.getBy(supplierDataStore.find(id)));
    }

}
