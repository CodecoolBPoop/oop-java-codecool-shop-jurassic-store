package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ShoppingCartSessionMap;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/", "/filter"})
public class ProductController extends HttpServlet {
    private ProductDao productDataStore = ProductDaoJdbc.getInstance();
    private ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
    private SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();
    private ShoppingCartSessionMap shoppingCartSessionMap = ShoppingCartSessionMap.getInstance();
    private ShoppingCartDaoMem shoppingCartDaoMem;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session=req.getSession();
        shoppingCartDaoMem = shoppingCartSessionMap.getShoppingCartBySession(session);

        context.setVariable("recipient", "World");
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());

        if (req.getParameter("category") == null && req.getParameter("supplier") == null) {
            context.setVariable("products", productDataStore.getAll());
        } else if (req.getParameter("category")!= null && req.getParameter("supplier") == null){
            filterByCategory(context, req);
        } else if (req.getParameter("category") == null && req.getParameter("supplier") != null) {
            filterBySupplier(context, req);
        } else if (req.getParameter("category") != null && req.getParameter("supplier") != null) {
            filterByCatAndSupp(context, req);
        }
        context.setVariable("cartItems", shoppingCartDaoMem.numberOfElements());
        engine.process("product/index.html", context, resp.getWriter());

    }

    private void filterByCategory(WebContext context, HttpServletRequest req) {
        if (req.getParameterValues("category").length > 1) {
            ArrayList<ProductCategory> categories = convertCategoryInput(req);
            context.setVariable("products", productDataStore.getBy(categories));
        } else {
            int id = Integer.parseInt(req.getParameter("category"));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(id)));
        }
    }

    private void filterBySupplier(WebContext context, HttpServletRequest req) {
        if (req.getParameterValues("supplier").length > 1) {
            List<Supplier> suppliers = convertSupplierInput(req);
            context.setVariable("products", productDataStore.getBy(suppliers));
        } else {
            int id = Integer.parseInt(req.getParameter("supplier"));
            context.setVariable("products", productDataStore.getBy(supplierDataStore.find(id)));
        }
    }

    private void filterByCatAndSupp(WebContext context, HttpServletRequest req) {
        List<Supplier> suppliers = convertSupplierInput(req);
        List<ProductCategory> categories = convertCategoryInput(req);
        context.setVariable("products", productDataStore.getBy(suppliers, categories));

    }

    private List<Supplier> convertSupplierInput(HttpServletRequest req) {
        int[] supplierIds = Arrays.asList(req.getParameterValues("supplier")).stream().mapToInt(Integer::parseInt).toArray();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        for (int id : supplierIds) {
            suppliers.add(supplierDataStore.find(id));
        }
        return suppliers;
    }

    private  ArrayList<ProductCategory> convertCategoryInput(HttpServletRequest req) {
        int[] categoryIds = Arrays.asList(req.getParameterValues("category")).stream().mapToInt(Integer::parseInt).toArray();
        ArrayList<ProductCategory> categories = new ArrayList<>();
        for (int id : categoryIds) {
            categories.add(productCategoryDataStore.find(id));
        }
        return categories;
    }

}
