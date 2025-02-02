package com.hajokns.domain.entity;

import com.hajokns.domain.vo.Money;

public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be at least 1");
        this.product = product;
        this.quantity = quantity;
    }

    public Money getTotalPrice() {
        return product.getPrice().multiply(quantity);
    }
}
