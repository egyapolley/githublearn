package com.example.egyapolley.github.retrofit_client;

import com.example.egyapolley.github.model.RepoDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoRestInterface {

    @GET("/users/{username}/repos")
    public Call<List<RepoDetails>> getrepos(@Path("username") String username);
}
