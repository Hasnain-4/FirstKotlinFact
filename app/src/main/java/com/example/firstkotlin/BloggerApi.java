package com.example.firstkotlin;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class BloggerApi {

    private static final String key = " ";
    private static final String url = "https://meta.etherealwork.net/top-ten-website/api/get-facts/";

    public static PostService postService=null;

    public static PostService getService()
    {
        if (postService==null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService =retrofit.create(PostService.class);

        }
        return postService;
    }

    public interface PostService
    {

        @GET("?key=" +key)
        Call<Json4Kotlin_Base> getpostList();
    }

}
