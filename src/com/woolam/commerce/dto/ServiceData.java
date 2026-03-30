package com.woolam.commerce.dto;

import com.woolam.commerce.domain.Cart;
import com.woolam.commerce.domain.Category;
import com.woolam.commerce.domain.Product;

public class ServiceData {
    // 속성
    private Category currentCategory;
    private Product currentProduct;
    private Cart cart;

    // Getter
    public Category getCurrentCategory() {
        return currentCategory;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public Cart getCart() {
        return cart;
    }

    // Setter
    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
