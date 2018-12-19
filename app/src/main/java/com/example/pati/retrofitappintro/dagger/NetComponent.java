package com.example.pati.retrofitappintro.dagger;

import com.example.pati.retrofitappintro.view.LoginActivity;
import com.example.pati.retrofitappintro.view.MainActivity;

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
}