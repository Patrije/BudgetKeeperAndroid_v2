package com.example.pati.retrofitappintro.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pati.retrofitappintro.R;
import com.example.pati.retrofitappintro.model.Transaction;
import com.example.pati.retrofitappintro.util.TimeHelper;

/**
 * Created by Pati on 12.12.2018.
 */

public class Dialog {

    private final Context context;
    private final TransactionViewModel transactionViewModel;
    private final String OperationType;
    private Spinner spinner;
    private String category;

    public Dialog(Context context, TransactionViewModel transactionViewModel, String operationType){
        this.context=context;
        this.transactionViewModel=transactionViewModel;
        this.OperationType=operationType;
    }

    public void showDialog() {

        final android.app.Dialog dialog = new android.app.Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        final EditText userInput = dialog.findViewById(R.id.value);
        //final EditText userCategory= dialog.findViewById(R.id.category);
        Button okButton = (Button) dialog.findViewById(R.id.confirmButton);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        spinner= (Spinner) dialog.findViewById(R.id.category);
        spinner.setOnItemSelectedListener(countrySelectedListener);
        okButton.setOnClickListener(v -> onSubmitted(dialog, Double.parseDouble(userInput.getText().toString()),category ));
        cancelButton.setOnClickListener(view -> onDismissed(dialog));
        dialog.show();
    }
    private void onSubmitted(final DialogInterface dialog, final Double value, final String category) {
        if(category.equals("Category")){transactionViewModel.insert(new Transaction(value,TimeHelper.getNow().getTimeInMillis(),23L,"N/A"));}
       else transactionViewModel.insert(new Transaction(value,TimeHelper.getNow().getTimeInMillis(),23L,category));
        dialog.dismiss();
    }

    private void onDismissed(final DialogInterface dialog) {
        dialog.dismiss();
    }

    AdapterView.OnItemSelectedListener countrySelectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> spinner, View container,
                                   int position, long id) {
           category= spinner.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    };
}
