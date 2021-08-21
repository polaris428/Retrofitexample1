package com.example.retrofitexample1;

import com.google.gson.annotations.SerializedName;

public class GithubRepository {
    long id;
    String name;
    String description;
    @SerializedName("html_url")
    String htmlUrl;
}
