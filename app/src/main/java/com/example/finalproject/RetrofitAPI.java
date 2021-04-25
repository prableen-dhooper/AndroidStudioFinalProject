package com.example.finalproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {
    // String BASE_URL = "http://www.omdbapi.com/";

    @GET
    Call<Movie> getMovie(@Url String url);

    @GET
    Call<String> getMovieAsString(@Url String url);

    // Call<List<Movie>> getsuperHeroes();
}
