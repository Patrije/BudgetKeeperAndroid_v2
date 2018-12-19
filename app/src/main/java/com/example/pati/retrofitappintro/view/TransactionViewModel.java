package com.example.pati.retrofitappintro.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.pati.retrofitappintro.model.Transaction;
import com.example.pati.retrofitappintro.repository.TransactionRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Pati on 12.11.2018.
 */

public class TransactionViewModel extends AndroidViewModel {

    private TransactionRepository transactionRepository;

    private Double transactionSum;
    private LiveData<List<Transaction>> transactionList;

    public TransactionViewModel(Application application) throws ExecutionException, InterruptedException {
        super(application);

        transactionRepository = new TransactionRepository(application);
        transactionSum = transactionRepository.getTransactionSum();
        transactionList = transactionRepository.getAllTransactions();
    }

    public void setUpViewModel() throws ExecutionException, InterruptedException {

    }

    public Double getTransactionSum() {
        return transactionSum;
    }

    public void insert(Transaction transaction) {
        transactionRepository.insertTransaction(transaction);
    }


    public LiveData<List<Transaction>> getAllTransactions() throws ExecutionException, InterruptedException {
        if (transactionList == null) {
            transactionList = transactionRepository.getAllTransactions();
        }
        return transactionList;
    }


    public void deleteTransactions() {
        transactionRepository.deleteTransactions();
    }

}
