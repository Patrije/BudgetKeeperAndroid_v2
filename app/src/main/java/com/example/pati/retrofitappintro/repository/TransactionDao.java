package com.example.pati.retrofitappintro.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.pati.retrofitappintro.model.Transaction;

import java.util.List;

/**
 * Created by Pati on 11.11.2018.
 */
@Dao
public interface TransactionDao {

    @Insert
    void insert(Transaction transaction);

    @Query("delete  from `Transaction` ")
    void deleteAll();

    @Query("select sum(value) from `Transaction` where isAsk=0")
    Double getSumOfTransaction();

    @Query("select transactionId,value,category,dateOfTransaction, employeeId, isAsk from `Transaction` order by dateOfTransaction desc")
    LiveData<List<Transaction>> getAllTransactions();

    @Query("select transactionId, value, category, dateOfTransaction, employeeId, isAsk from `Transaction` order by dateOfTransaction asc")
    List<Transaction> getAllTransactionASC();

    @Query("select transactionId, value, category, dateOfTransaction, employeeId, isAsk from `Transaction` where isAsk=0 order by dateOfTransaction asc")
    LiveData<List<Transaction>> getAllTransactionsWithoutRequests();
}