package com.example.gyogynovenykisokos;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/api/species-list")
    Call<ResponseBody> getData(@Query("key") String key,
                               @Query("page") int page);
}
