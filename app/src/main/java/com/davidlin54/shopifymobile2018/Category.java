package com.davidlin54.shopifymobile2018;

import java.util.List;

public class Category {
    private String key;
    private List<Order> orders;

    public Category(String key, List<Order> orders) {
        this.key = key;
        this.orders = orders;
    }

    public String getKey() {
        return key;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
