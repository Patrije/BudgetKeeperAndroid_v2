package com.example.pati.retrofitappintro.service;

import com.example.pati.retrofitappintro.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
