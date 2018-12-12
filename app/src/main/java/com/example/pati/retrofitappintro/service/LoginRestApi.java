package com.example.pati.retrofitappintro.service;

import com.example.pati.retrofitappintro.model.LoginCredentials;
import com.example.pati.retrofitappintro.model.Transaction;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Pati on 12.12.2018.
 */

public interface LoginRestApi {

    @POST("api/employee/login")
    Call<ResponseBody> checkLoginCredentails(@Body LoginCredentials loginCredentials);

}
