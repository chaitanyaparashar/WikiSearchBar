package com.example.tricogassignment.api;

import com.example.tricogassignment.api.model.JsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIService {

    @GET
    Call<JsonResponse> getTracks(@Url String url);
}