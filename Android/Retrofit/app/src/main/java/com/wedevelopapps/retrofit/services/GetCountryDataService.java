package com.wedevelopapps.retrofit.services;

import com.wedevelopapps.retrofit.Model.info;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryDataService {

    @GET("country/get/all")
    Call<info> getResult();
}
