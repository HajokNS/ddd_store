package com.hajokns.domain.vo;

import java.util.Objects;

public final class Dimensions {
    private final double length;
    private final double width;
    private final double height;

    public Dimensions(double length, double width, double height) {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive values");
        }
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getVolume() {
        return length * width * height;
    }

    @Override
    public String toString() {
        return length + "x" + width + "x" + height + " cm";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dimensions)) return false;
        Dimensions that = (Dimensions) o;
        return Double.compare(that.length, length) == 0 &&
            Double.compare(that.width, width) == 0 &&
            Double.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }
}
