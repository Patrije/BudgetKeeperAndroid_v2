package com.example.pati.retrofitappintro.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.pati.retrofitappintro.R;
import com.example.pati.retrofitappintro.model.Transaction;
import com.example.pati.retrofitappintro.util.TimeHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Pati on 10.12.2018.
 */

public class MyLineChart extends LineChart {

    private Context context;
    private List<Transaction> transactionList;

    public MyLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setData() {
        ArrayList<Entry> entries = new ArrayList<>();
        Calendar calendar = TimeHelper.getNow();
        LineDataSet set = new LineDataSet(entries, "datas");
        set.setColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);
        LineData data = new LineData(dataSets);

    }

//    public void updateData(List<Transac newData) {
//        this.transactionList = newData;
//        notifyDataSetChanged();
//    }

}
