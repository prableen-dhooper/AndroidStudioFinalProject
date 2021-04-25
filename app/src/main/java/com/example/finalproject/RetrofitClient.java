package com.example.finalproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private RetrofitAPI myApi;

    /*
    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(RetrofitAPI.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public RetrofitAPI getMyApi() {
        return myApi;
    }
    */
}
