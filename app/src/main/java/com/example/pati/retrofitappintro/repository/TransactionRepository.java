package com.example.pati.retrofitappintro.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.pati.retrofitappintro.model.Transaction;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Pati on 11.12.2018.
 */

public class TransactionRepository {
    private TransactionDao transactionDao;
    private Double transactionSum;

    public TransactionRepository(Application application) {
        TransactionDatabase db = TransactionDatabase.getDatabase(application);
        transactionDao = db.transactionDao();

    }

    public Double getTransactionSum() throws ExecutionException, InterruptedException {
        return new getSum(transactionDao).execute().get();
    }

    public void insertTransaction(Transaction transaction) {
        new insertAsyncTask(transactionDao).execute(transaction);
    }

    public void deleteTransactions(){
        new deleteAsyncTask(transactionDao).execute();
    }

    public List<Transaction> getAllTransactions() throws ExecutionException, InterruptedException {
        return new getTransactions(transactionDao).execute().get();
    }

    private static class insertAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDao mAsyncTaskDao;

        insertAsyncTask(TransactionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Transaction... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private TransactionDao mAsyncTaskDao;

        deleteAsyncTask(TransactionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class getSum extends AsyncTask<Void, Void, Double> {

        private TransactionDao mAsyncTaskDao;

        getSum(TransactionDao dao){
            mAsyncTaskDao= dao;
        }


        @Override
        protected Double doInBackground(Void... voids) {
            return mAsyncTaskDao.getSumOfTransaction();
        }
    }

    private static class getTransactions extends AsyncTask<Void, Void, List<Transaction>> {

        private TransactionDao mAsyncTaskDao;

        getTransactions(TransactionDao dao){
            mAsyncTaskDao= dao;
        }


        @Override
        protected List<Transaction> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAllTransactions();
        }
    }
}
