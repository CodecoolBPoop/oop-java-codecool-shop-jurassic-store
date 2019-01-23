package com.codecool.shop.model;

public class ShoppingCartElement {
    private int quantity;
    private Product product;

    ShoppingCartElement(Product product, int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }
}
