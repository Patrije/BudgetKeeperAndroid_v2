package com.example.pati.BudgetKeeperAndroid.dagger;

import com.example.pati.BudgetKeeperAndroid.service.TransactionService;
import com.example.pati.BudgetKeeperAndroid.view.LoginActivity;
import com.example.pati.BudgetKeeperAndroid.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Pati on 10.11.2018.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);

    void inject(LoginActivity loginActivity);

    void inject(TransactionService transactionService);
}