package com.davidlin54.shopifymobile2018;

import java.util.Comparator;

public class OrderNumberComparator implements Comparator<Order> {
    @Override
    public int compare(Order order, Order t1) {
        if (order.getOrderNumber() > t1.getOrderNumber()) {
            return -1;
        } else if (order.getOrderNumber() < t1.getOrderNumber()) {
            return 1;
        }

        return 0;
    }
}
