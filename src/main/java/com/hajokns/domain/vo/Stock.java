package com.hajokns.domain.vo;

public final class Stock {
    private int quantity;

    public Stock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public void increase(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Cannot add negative stock");
        this.quantity += amount;
    }

    public void decrease(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Cannot remove negative stock");
        if (amount > this.quantity) throw new IllegalArgumentException("Not enough stock available");
        this.quantity -= amount;
    }

    public boolean isAvailable(int required) {
        return this.quantity >= required;
    }

    @Override
    public String toString() {
        return "Stock: " + quantity + " units";
    }
}
