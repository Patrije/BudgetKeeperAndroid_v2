package com.example.pati.retrofitappintro.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.pati.retrofitappintro.R;
import com.example.pati.retrofitappintro.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HistoryActivity extends AppCompatActivity {

    private TransactionViewModel transactionViewModel;
    private RecyclerView recyclerView;
    private  CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        try {
            transactionViewModel=new TransactionViewModel(getApplication());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      customAdapter= new CustomAdapter(getApplicationContext(),transactionViewModel.getAllTransactions());
recyclerView.setAdapter(customAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        Button buttonDelete= (Button)findViewById(R.id.deleteButton);
        buttonDelete.setOnClickListener(v->{
            transactionViewModel.deleteTransactions();
            customAdapter.updateEvents(transactionViewModel.getAllTransactions());
            customAdapter.clear();
        });
    }
}
