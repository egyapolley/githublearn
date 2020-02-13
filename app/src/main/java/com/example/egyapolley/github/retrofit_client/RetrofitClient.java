package com.example.egyapolley.github.retrofit_client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.github.com/users/";

    private static Retrofit client;

    public static Retrofit getclient() {
        if (client == null) {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        } else return client;


    }

    private RetrofitClient() {

    }
}
