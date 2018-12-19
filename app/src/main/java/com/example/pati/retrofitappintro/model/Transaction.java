package com.example.pati.retrofitappintro.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Pati on 11.11.2018.
 */

@Entity
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private int transactionId;
    private double value;
    private Long dateOfTransaction;
    private Long employeeId;
    private String category;

    @Ignore
    public Transaction(double value, Long dateOfTransaction, Long employeeId, String category) {
        this.value = value;
        this.dateOfTransaction = dateOfTransaction;
        this.employeeId = employeeId;
        this.category = category;
    }

    public Transaction(double value, Long dateOfTransaction, String category) {
        this.value = value;
        this.dateOfTransaction = dateOfTransaction;
        this.category = category;
    }

    @Ignore
    public Transaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
