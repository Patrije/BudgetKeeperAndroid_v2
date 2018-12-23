package com.example.pati.retrofitappintro.service;

import com.example.pati.retrofitappintro.model.Transaction;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Pati on 22.11.2018.
 */

public interface TransactionRestApi {

    @POST("api/transaction/add")
    Call<ResponseBody> sentTransaction(@Body Transaction transaction);

    @POST("api/transaction/addAllTransactions")
    Call<ResponseBody> sentTransactions(@Body List<Transaction> transactionList);

}
