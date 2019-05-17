package com.example.tricogassignment.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    public static APIService getInstance() {

        String baseUrl = "https://en.wikipedia.org//w/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(APIService.class);
    }
}