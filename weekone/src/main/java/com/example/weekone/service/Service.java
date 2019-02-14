package com.example.weekone.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Service {
    @GET
    Call<ResponseBody> Seekget(@Url String url, @QueryMap Map<String ,String > map);

}
