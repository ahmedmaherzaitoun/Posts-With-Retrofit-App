package com.example.jsondemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClinet {

    public static final String URL = "https://jsonplaceholder.typicode.com/";

    public static Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static Retrofit getClientComment(int postId){
        final String URLComments = "https://jsonplaceholder.typicode.com/posts/" + postId + "/";
        return new Retrofit.Builder()
                .baseUrl(URLComments)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
