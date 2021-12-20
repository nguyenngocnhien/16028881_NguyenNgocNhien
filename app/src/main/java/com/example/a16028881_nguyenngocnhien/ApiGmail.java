package com.example.a16028881_nguyenngocnhien;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiGmail {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiGmail apiService = new Retrofit.Builder()
            .baseUrl("https://61bf3931b25c3a00173f4d2a.mockapi.io/user/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiGmail.class);

    @GET("gmail/{id}")
    Call<Gmail> getBillById(@Path("id") String id);

    @GET("gmail")
    Call<List<Gmail>> getBills();

    @POST("gmail")
    Call<Gmail> addGmail(@Body Gmail bill);

    @PUT("gmail/{id}")
    Call<Gmail> update(@Path("id") String id, @Body Gmail bill);

    @DELETE("gmail/{id}")
    Call<Gmail> delete(@Path("id") String id);

    //======
    @GET("user/{id}")
    Call<User> getUserById(@Path("id") String id);

    @GET("user")
    Call<List<User>> getUsers();

    @POST("user")
    Call<User> addUser(@Body User bill);
}