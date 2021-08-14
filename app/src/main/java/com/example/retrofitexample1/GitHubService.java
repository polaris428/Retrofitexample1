package com.example.retrofitexample1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{user}")
    Call<GithubUser> getUser(@Path("user")String user);



}
