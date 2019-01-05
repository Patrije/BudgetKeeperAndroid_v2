package com.example.pati.BudgetKeeperAndroid.dagger;

import android.app.Application;

/**
 * Created by Pati on 10.11.2018.
 */

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://192.168.1.105:8080/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}