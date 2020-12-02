package com.collinson.vendingmachine.model;

import java.math.BigDecimal;

public class Product {
    private String code;
    private String name;
    private BigDecimal price;

    public Product() {
    }

    public Product(String name, String code, BigDecimal price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\t").append(" | ").append(code).append("\t | ").append(price).append("\t");
        return sb.toString();
    }
}
