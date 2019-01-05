package com.example.pati.BudgetKeeperAndroid.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.pati.BudgetKeeperAndroid.model.Transaction;
import com.example.pati.BudgetKeeperAndroid.repository.TransactionRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Pati on 12.11.2018.
 */

public class TransactionViewModel extends AndroidViewModel {

    private static final String LOGIN_KEY="BudgetKeeperKey";
    private TransactionRepository transactionRepository;
    private Double transactionSum;
    private LiveData<List<Transaction>> transactionList;
    private LiveData<List<Transaction>> transactionListWithoutRequest;
    private SharedPreferences sharedPreferences;

    public TransactionViewModel(Application application) throws ExecutionException, InterruptedException {
        super(application);

        transactionRepository = new TransactionRepository(application);
        transactionSum = transactionRepository.getTransactionSum();
        transactionList = transactionRepository.getAllTransactions();
        transactionListWithoutRequest= transactionRepository.getAllTransactionsList();
        sharedPreferences = application.getSharedPreferences("login_pref",Context.MODE_PRIVATE);
    }

    public void setUpViewModel() throws ExecutionException, InterruptedException {

    }

    public void saveToSharedPref(String login){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOGIN_KEY,login);
        editor.commit();
    }

    public String getFromSharedPref(){
return sharedPreferences.getString(LOGIN_KEY,"");
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

    public LiveData<List<Transaction>> getAllTransactionsWithoutRequest() throws ExecutionException, InterruptedException {
        if (transactionListWithoutRequest == null) {
            transactionListWithoutRequest = transactionRepository.getAllTransactionsList();
        }
        return transactionListWithoutRequest;
    }


    public void deleteTransactions() {
        transactionRepository.deleteTransactions();
    }

}
