package com.davidlin54.shopifymobile2018;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderSummaryService {
    @GET("orders.json")
    Call<Orders> listOrders(@Query("access_token") String access_token);
}
