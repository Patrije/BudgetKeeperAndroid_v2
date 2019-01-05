package com.example.pati.BudgetKeeperAndroid.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import android.content.Context;

import com.example.pati.BudgetKeeperAndroid.model.Transaction;

/**
 * Created by Pati on 11.11.2018.
 */

@Database(entities = {Transaction.class}, version = 1)
public abstract class TransactionDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();

    private static volatile TransactionDatabase INSTANCE;

    public static TransactionDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TransactionDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TransactionDatabase.class, "transaction_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
