package com.example.pati.retrofitappintro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Pati on 22.11.2018.
 */

public interface JsonPlaceHolderApi {

    @GET("employees")
    Call<List<Employee>> getAllEmployees();

}
