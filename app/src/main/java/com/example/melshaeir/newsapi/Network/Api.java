package com.example.melshaeir.newsapi.Network;



import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {
    @GET("sources")
    Call<ResponseBody> getNews(@QueryMap Map<String,String> params);
    @GET("top-headlines")
    Call<ResponseBody> getArticles(@QueryMap Map<String,String> params);

}
