package com.example.courseboard.youtube;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("search")
    Call<Example> getHeadlines(
            @Query("q") String query,
            @Query("chart") String chart,
            @Query("apiKey") String apiKey
    );

}
