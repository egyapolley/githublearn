package com.example.egyapolley.github.retrofit_client;

import com.example.egyapolley.github.model.UserDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface UserRestInterface {

    @GET("/users/{username}")
    public Call<UserDetails> getUser(@Path("username") String username);
}
