package com.hajokns.domain.vo;

import java.util.Objects;

public final class ProductDetails {
    private final String name;
    private final String description;
    private final Dimensions dimensions;

    public ProductDetails(String name, String description, Dimensions dimensions) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        this.name = name;
        this.description = Objects.requireNonNullElse(description, "No description available");
        this.dimensions = dimensions;
    }

    public String getShortDescription() {
        return name + " - " + description;
    }

    @Override
    public String toString() {
        return name + " (" + dimensions + "): " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDetails)) return false;
        ProductDetails that = (ProductDetails) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(dimensions, that.dimensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, dimensions);
    }
}
