package com.hajokns.domain.aggregate;

import com.hajokns.domain.entity.Customer;
import com.hajokns.domain.entity.OrderItem;
import com.hajokns.domain.entity.Product;
import com.hajokns.domain.vo.Address;
import com.hajokns.domain.vo.Money;
import com.hajokns.domain.vo.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderAggregate {
    private final UUID id;
    private final Customer customer;
    private final List<OrderItem> items;
    private Money totalPrice;
    private OrderStatus status;
    private Address shippingAddress;

    public OrderAggregate(Customer customer, Address shippingAddress) {
        this.id = UUID.randomUUID();
        this.customer = Objects.requireNonNull(customer, "Customer cannot be null");
        this.shippingAddress = Objects.requireNonNull(shippingAddress, "Shipping address cannot be null");
        this.items = new ArrayList<>();
        this.totalPrice = new Money("USD", BigDecimal.ZERO);
        this.status = OrderStatus.NEW;
    }

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void addItem(Product product, int quantity) {
        Objects.requireNonNull(product, "Product cannot be null");
        if (!product.isInStock(quantity)) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        OrderItem item = new OrderItem(product, quantity);
        items.add(item);
        product.reduceStock(quantity);
        recalculateTotal();
    }

    public void removeItem(OrderItem item) {
        Objects.requireNonNull(item, "Order item cannot be null");
        if (!items.contains(item)) {
            throw new IllegalArgumentException("Item not found in the order");
        }

        items.remove(item);
        recalculateTotal();
    }

    public void updateShippingAddress(Address newAddress) {
        Objects.requireNonNull(newAddress, "New address cannot be null");
        if (status == OrderStatus.SHIPPED || status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot change address for a shipped or delivered order");
        }
        this.shippingAddress = newAddress;
    }

    public void changeStatus(OrderStatus newStatus) {
        Objects.requireNonNull(newStatus, "New status cannot be null");
        if (status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot change status of a delivered order");
        }
        this.status = newStatus;
    }

    private void recalculateTotal() {
        totalPrice = items.stream()
            .map(OrderItem::getTotalPrice)
            .reduce(new Money("USD", BigDecimal.ZERO), Money::add);
    }

    @Override
    public String toString() {
        return "OrderAggregate{" +
            "id=" + id +
            ", customer=" + customer.getFullName() +
            ", status=" + status +
            ", totalPrice=" + totalPrice +
            ", shippingAddress=" + shippingAddress +
            ", items=" + items.size() +
            '}';
    }
}
