package com.example.pati.retrofitappintro.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.pati.retrofitappintro.model.Transaction;

import java.util.List;

/**
 * Created by Pati on 11.12.2018.
 */
@Dao
public interface TransactionDao {

    @Insert
    void insert(Transaction transaction);

    @Query("delete  from `Transaction` ")
    public void deleteAll();
    @Query("select sum(value) from `Transaction`")
    Double getSumOfTransaction();

    @Query("select transactionId,value,category,dateOfTransaction from `Transaction` order by dateOfTransaction asc")
    List<Transaction> getAllTransactions();

}