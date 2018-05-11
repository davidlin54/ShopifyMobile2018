package com.davidlin54.shopifymobile2018;

import java.util.List;

public interface OrderSummaryPresenter {
    void fetchOrders();
    List<Category> getOrdersByYear();
    List<Category> getOrdersByProvince();
}
