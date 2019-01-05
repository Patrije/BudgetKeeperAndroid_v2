package com.example.pati.BudgetKeeperAndroid.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pati on 11.11.2018.
 */

@Entity
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("transactionId")
    private long transactionId;
    @SerializedName("value")
    private double value;
    @SerializedName("dateOfTransaction")
    private Long dateOfTransaction;
    @SerializedName("employeeLogin")
    private String employeeLogin;
    @SerializedName("category")
    private String category;
    @SerializedName("isAsk")
    private int isAsk;


    public Transaction(double value, Long dateOfTransaction, String employeeLogin, String category, int isAsk) {
        this.value = value;
        this.dateOfTransaction = dateOfTransaction;
        this.employeeLogin = employeeLogin;
        this.category = category;
        this.isAsk=isAsk;
    }

    @Ignore
    public Transaction(double value, Long dateOfTransaction, String category) {
        this.value = value;
        this.dateOfTransaction = dateOfTransaction;
        this.category = category;
    }

    @Ignore
    public Transaction() {
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Long getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Long dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getEmployeeLogin() {
        return employeeLogin;
    }

    public void setEmployeeId(String employeeLogin) {
        this.employeeLogin = employeeLogin;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIsAsk() {
        return isAsk;
    }

    public void setIsAsk(int ask) {
        isAsk = ask;
    }
}
