package com.example.pati.BudgetKeeperAndroid.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.pati.BudgetKeeperAndroid.R;
import com.example.pati.BudgetKeeperAndroid.model.Transaction;
import com.example.pati.BudgetKeeperAndroid.repository.TransactionRepository;
import com.example.pati.BudgetKeeperAndroid.util.TimeHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView budget;
    private Button askButton, expenseButton, incomeButton, historyButton;
    private TransactionViewModel transactionViewModel;
    private TransactionRepository transactionRepository;
    private Dialog dialog1, dialog2, dialog3;
    private LineChart lineChart;
    private ArrayList<Entry> entries, negEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askButton = (Button) findViewById(R.id.ask_button);
        historyButton = (Button) findViewById(R.id.history_button);
        budget = (TextView) findViewById(R.id.budget);
        expenseButton = (Button) findViewById(R.id.expense_button);
        incomeButton = (Button) findViewById(R.id.income_button);
        lineChart = (LineChart) findViewById(R.id.bar_chart);
        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        transactionRepository = new TransactionRepository(getApplication());
        dialog1 = new Dialog(this, transactionViewModel, "income");
        dialog2 = new Dialog(this, transactionViewModel, "expenses");
        dialog3 = new Dialog(this, transactionViewModel, "request");
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3.showDialog();
            }

        });
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.showDialog();
            }

        });

        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.showDialog();
            }

        });

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.setBackgroundColor(ColorTemplate.getHoloBlue());
        lineChart.setAlpha(0.8f);
        entries = new ArrayList<>();
        negEntries= new ArrayList<>();
        Calendar calendar = TimeHelper.getNow();
        try {
            transactionViewModel.getAllTransactions().observe(this, new Observer<List<Transaction>>() {
                @Override
                public void onChanged(@Nullable List<Transaction> transactions) {
                    try {
                        budget.setText(transactionRepository.getTransactionSum().toString()+"  PLN");
//                        String string = "";
//                        entries.removeAll(entries);
//                        negEntries.removeAll(negEntries);
//                        for (int i = 0; i < transactions.size(); i++) {
//                            if(transactionRepository.getAllTransactionASC().get(i).getValue()>=0) {
//                                entries.add(new Entry((float) i, (float) transactionRepository.getAllTransactionASC().get(i).getValue()));
//                            } else {
//                                negEntries.add(new Entry((float) i, Math.abs((float) transactionRepository.getAllTransactionASC().get(i).getValue())));
//                            }
//                        }
//
//                        LineDataSet set = new LineDataSet(entries, "Incomes");
//                        LineDataSet negSet = new LineDataSet(negEntries, "Expenses");
//                        negSet.setColor(Color.RED);
//                        negSet.setLineWidth(3.0f);
//                        negSet.setDrawFilled(true);
//                        negSet.setFillColor(Color.RED);
//                        negSet.setFillAlpha(210);
//                        set.setColor(Color.GREEN);
//                        set.setLineWidth(3.0f);
//                        set.setDrawFilled(true);
//                        set.setFillColor(Color.GREEN);
//                        set.setFillAlpha(190);
//                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//                        dataSets.add(set);
//                        dataSets.add(negSet);
//                        LineData data = new LineData(dataSets);
//                        lineChart.setData(data);
//                        lineChart.notifyDataSetChanged();
//                        lineChart.invalidate();
                    }// catch (NegativeArraySizeException e) {
                        //Log.i("Status", e.getMessage());
                   // }
                    catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        budget.setText("00.00");
                    }
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            transactionViewModel.getAllTransactionsWithoutRequest().observe(this, new Observer<List<Transaction>>() {
                @Override
                public void onChanged(@Nullable List<Transaction> transactions) {
                    try {
                       // budget.setText(transactionRepository.getTransactionSum().toString()+"  PLN");
                        String string = "";
                        entries.removeAll(entries);
                        negEntries.removeAll(negEntries);
                        for (int i = 0; i < transactions.size(); i++) {
                            if(transactions.get(i).getValue()>=0) {
                                entries.add(new Entry((float) i, (float) transactions.get(i).getValue()));
                            } else {
                                negEntries.add(new Entry((float) i, Math.abs((float) transactions.get(i).getValue())));
                            }
                        }

                        LineDataSet set = new LineDataSet(entries, "Incomes");
                        LineDataSet negSet = new LineDataSet(negEntries, "Expenses");
                        negSet.setColor(Color.RED);
                        negSet.setLineWidth(3.0f);
                        negSet.setDrawFilled(true);
                        negSet.setFillColor(Color.RED);
                        negSet.setFillAlpha(210);
                        set.setColor(Color.GREEN);
                        set.setLineWidth(3.0f);
                        set.setDrawFilled(true);
                        set.setFillColor(Color.GREEN);
                        set.setFillAlpha(190);
                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                        dataSets.add(set);
                        dataSets.add(negSet);
                        LineData data = new LineData(dataSets);
                        lineChart.setData(data);
                        lineChart.notifyDataSetChanged();
                        lineChart.invalidate();
                    } catch (NegativeArraySizeException e) {
                        Log.i("Status", e.getMessage());
                    }  catch (NullPointerException e) {
                       // budget.setText("00.00");
                    }
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(intent);
        });
    }

    public void updateEntries(ArrayList<Entry> newEntries) {
        this.entries = newEntries;
        lineChart.notifyDataSetChanged();
    }
}
