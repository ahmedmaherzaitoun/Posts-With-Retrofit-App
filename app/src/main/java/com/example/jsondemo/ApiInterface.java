package com.example.jsondemo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    // Get > ( notrmal data )
    // Post >( sensitive data)  login username ,password  , many parameters
    @GET("posts")
    Call<Post[]> getPosts();
    @GET("comments")
    Call<Comment[]> getComment();



}
