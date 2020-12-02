package com.collinson.vendingmachine;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.collinson.vendingmachine.model.Product;


public class ProductsDB {

    private static final Set<Product> products = new HashSet<>();

    public static void addProduct(Product p) {
        products.add(p);
    }

    public static Optional<Product> getProduct(String code) {
        for (Product p : products) {
            if (p.getCode().equalsIgnoreCase(code)) {
                return Optional.ofNullable(p);
            }
        }
        return Optional.empty();
    }

	public static void clear() {
        products.clear();
	}

	public static Set<Product> getAll() {
		return products;
	}

}
