package com.example.pati.retrofitappintro.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.pati.retrofitappintro.R;
import com.example.pati.retrofitappintro.model.Transaction;
import com.example.pati.retrofitappintro.repository.TransactionRepository;
import com.example.pati.retrofitappintro.service.TransactionService;
import com.example.pati.retrofitappintro.util.TimeHelper;

/**
 * Created by Pati on 12.11.2018.
 */

public class Dialog {

    private final Context context;
    private final TransactionViewModel transactionViewModel;
    private final String operationType;
    private TransactionRepository transactionRepository;
    private Spinner spinner;
    private String category;
    private EditText userInput;


    public Dialog(Context context, TransactionViewModel transactionViewModel, String operationType) {
        this.context = context;
        this.transactionViewModel = transactionViewModel;
        this.operationType = operationType;
    }

    public void showDialog() {
        final android.app.Dialog dialog = new android.app.Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        userInput = dialog.findViewById(R.id.value);
        Button okButton = (Button) dialog.findViewById(R.id.confirmButton);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        spinner = (Spinner) dialog.findViewById(R.id.category);
        spinner.setOnItemSelectedListener(categorySelectedListener);
        okButton.setOnClickListener(v -> onSubmitted(dialog, ParseDouble(userInput.getText().toString()), category));
        cancelButton.setOnClickListener(view -> onDismissed(dialog));
        dialog.show();
    }

   private double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        else return 0;
    }
    private void onSubmitted(final DialogInterface dialog, Double value, final String category) {
        int isAsk= 0;
        if(value==0){
            Toast.makeText(context.getApplicationContext(),"Insert value", Toast.LENGTH_SHORT).show();
            return ;
        }
        Intent intentService = new Intent(context.getApplicationContext(), TransactionService.class);
        context.startService(intentService);
            if (operationType.equals("expenses")) {
                value = -value;
            }
        if (operationType.equals("request")) {
            isAsk=1;
        }
            if (category.equals("Category")) {
                transactionViewModel.insert(new Transaction(value, TimeHelper.getNow().getTimeInMillis(), TimeHelper.getNow().getTimeInMillis(), "N/A", isAsk));
            } else
                transactionViewModel.insert(new Transaction(value, TimeHelper.getNow().getTimeInMillis(), 23L, category, isAsk));
        dialog.dismiss();
    }
    private void onDismissed(final DialogInterface dialog) {
        dialog.dismiss();
    }
    AdapterView.OnItemSelectedListener categorySelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> spinner, View container, int position, long id) {
            category = spinner.getSelectedItem().toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    };
}
