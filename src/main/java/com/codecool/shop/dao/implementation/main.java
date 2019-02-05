package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;

public class main {
    public static void main(String[] args) {
        ProductCategoryDaoJdbc jdbc = new ProductCategoryDaoJdbc();
        ProductCategory herbivorous = new ProductCategory("Herbivorous", "Dinosaur", "Vegan");
        ProductCategory carnivorous = new ProductCategory("Carnivorous", "Dinosaur", "Not vegan");
        ProductCategory omnivorous = new ProductCategory("Omnivorous", "Dinosaur", "Not that vegan");

        jdbc.add(herbivorous);
        jdbc.add(carnivorous);
        jdbc.add(omnivorous);

    }
}
