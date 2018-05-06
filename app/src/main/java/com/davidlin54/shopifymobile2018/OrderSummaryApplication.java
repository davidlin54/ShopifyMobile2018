package com.davidlin54.shopifymobile2018;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderSummaryApplication extends Application {
    public static OrderSummaryService mService;
    public final static String API_BASE_URL = "https://shopicruit.myshopify.com/admin/";
    public final static String ACCESS_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";

    public OrderSummaryApplication() {
        super();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create(gson)
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        mService = retrofit.create(OrderSummaryService.class);
    }
}
