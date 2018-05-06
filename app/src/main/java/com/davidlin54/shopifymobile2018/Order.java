package com.davidlin54.shopifymobile2018;

import java.util.Date;

public class Order {
    private Address shipping_address;
    private int order_number;
    private String financial_status;
    private String fulfillment_status;
    private double total_price;
    private Date processed_at;

    public Address getShippingAddress() {
        return shipping_address;
    }

    public int getOrderNumber() {
        return order_number;
    }

    public String getFinancialStatus() {
        return financial_status;
    }

    public String getFulfillmentStatus() {
        return fulfillment_status;
    }

    public double getTotalPrice() {
        return total_price;
    }

    public Date getProcessedAt() {
        return processed_at;
    }
}
