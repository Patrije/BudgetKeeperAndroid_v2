package com.example.pati.retrofitappintro.service;

import com.example.pati.retrofitappintro.model.LoginCredentials;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Pati on 12.12.2018.
 */

public interface LoginRestApi {

    @POST("api/employee/login")
    Call<ResponseBody> checkLoginCredentials(@Body LoginCredentials loginCredentials);

}
