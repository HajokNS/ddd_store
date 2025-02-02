package com.hajokns.domain.entity;

import com.hajokns.domain.vo.Money;
import com.hajokns.domain.vo.ProductDetails;
import com.hajokns.domain.vo.Stock;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final ProductDetails details;
    private Money price;
    private final Stock stock;

    public Product(ProductDetails details, Money price, Stock stock) {
        this.id = UUID.randomUUID();
        this.details = Objects.requireNonNull(details, "Product details cannot be null");
        this.price = Objects.requireNonNull(price, "Price cannot be null");
        this.stock = Objects.requireNonNull(stock, "Stock cannot be null");
    }

    public UUID getId() {
        return id;
    }

    public ProductDetails getDetails() {
        return details;
    }

    public Money getPrice() {
        return price;
    }

    public void updatePrice(Money newPrice) {
        if (newPrice == null || newPrice.getAmount().compareTo(price.getAmount()) < 0) {
            throw new IllegalArgumentException("New price cannot be lower than the current price");
        }
        this.price = newPrice;
    }

    public boolean isInStock(int quantity) {
        return stock.isAvailable(quantity);
    }

    public void reduceStock(int quantity) {
        stock.decrease(quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", details=" + details +
            ", price=" + price +
            ", stock=" + stock +
            '}';
    }
}
