package com.example.pati.BudgetKeeperAndroid.service;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import com.example.pati.BudgetKeeperAndroid.dagger.App;
import com.example.pati.BudgetKeeperAndroid.repository.TransactionRepository;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Pati on 23.12.2018.
 */

public class TransactionService extends Service {

    @Inject
    Retrofit retrofit;
    private Context context;
    private TransactionRepository transactionRepository;
    private TransactionRestApi transactionRestApi;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        ((App) context).getNetComponent().inject(this);
        transactionRepository=new TransactionRepository((Application)context);
        transactionRestApi= retrofit.create(TransactionRestApi.class);
        Log.i("statussss", "sentttt");
        handler= new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    sentTransaction();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Log.i("statussss", "sentttt");

        return super.onStartCommand(intent, flags, startId);
    }

    private void sentTransaction() throws ExecutionException, InterruptedException {

        Call<ResponseBody> call = transactionRestApi.sentTransactions(transactionRepository.getAllTransactionASC());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                   // Toast.makeText(context,"Transaction sent",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
