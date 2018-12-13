package com.example.pati.retrofitappintro.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.pati.retrofitappintro.R;
import com.example.pati.retrofitappintro.model.Transaction;
import com.example.pati.retrofitappintro.repository.TransactionRepository;
import com.example.pati.retrofitappintro.util.TimeHelper;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView budget;
    private EditText loginEdit, balanceEdit;
    private Button askButton, expenseButton, incomeButton, historyButton;
    private TransactionViewModel transactionViewModel;
    private TransactionRepository transactionRepository;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askButton = (Button) findViewById(R.id.ask_button);
        historyButton = (Button) findViewById(R.id.history_button);
        budget = (TextView) findViewById(R.id.budget);
        expenseButton = (Button) findViewById(R.id.expense_button);
        incomeButton = (Button) findViewById(R.id.income_button);

            transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);

        transactionRepository=new TransactionRepository(getApplication());

        dialog= new Dialog(this, transactionViewModel,"dgs");
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.showDialog();
            }

        });
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.showDialog();
            }

        });

        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.showDialog();
            }

        });
        try {
            transactionViewModel.getAllTransactions().observe(this, new Observer<List<Transaction>>() {
                @Override
                public void onChanged(@Nullable List<Transaction> transactions) {
                    try {
                        budget.setText(transactionRepository.getTransactionSum().toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch(NullPointerException e){
budget.setText("00.00");                    }
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Double sum;
//        if (transactionViewModel.getTransactionSum() != null) {
//            sum = transactionViewModel.getTransactionSum();
//        } else {
//            sum = 0D;
//        }
//        budget.setText(sum.toString());
        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(intent);
        });
    }
    public void setSum(){
        Double sum;
        if (transactionViewModel.getTransactionSum() != null) {
            sum = transactionViewModel.getTransactionSum();
        } else {
            sum = 0D;
        }
        budget.setText(sum.toString());
    }



}
