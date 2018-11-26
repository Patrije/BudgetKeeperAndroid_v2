package com.example.pati.retrofitappintro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Pati on 22.11.2018.
 */

public interface EmployeeRestApi {

    @GET("employees")
    Call<List<Employee>> getAllEmployees();


    @POST("employees")
    Call<Employee> createEmployee(@Body Employee employee);

}
