package com.example.pati.retrofitappintro.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.pati.retrofitappintro.R;
import com.example.pati.retrofitappintro.model.Transaction;
import com.example.pati.retrofitappintro.util.TimeHelper;

/**
 * Created by Pati on 12.12.2018.
 */

public class Dialog {

    private final Context context;
    private final TransactionViewModel transactionViewModel;

    public Dialog(Context context, TransactionViewModel transactionViewModel){
        this.context=context;
        this.transactionViewModel=transactionViewModel;
    }

    public void showDialog() {

        final android.app.Dialog dialog = new android.app.Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        final EditText userInput = dialog.findViewById(R.id.value);
        final EditText userCategory= dialog.findViewById(R.id.category);
        Button okButton = (Button) dialog.findViewById(R.id.confirmButton);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        okButton.setOnClickListener(v -> onSubmitted(dialog, Double.parseDouble(userInput.getText().toString()), userCategory.getText().toString()));
        cancelButton.setOnClickListener(view -> onDismissed(dialog));
        dialog.show();
    }
    private void onSubmitted(final DialogInterface dialog, final Double value, final String category) {

        transactionViewModel.insert(new Transaction(value,TimeHelper.getNow().getTimeInMillis(),23L,category));
        dialog.dismiss();
    }

    private void onDismissed(final DialogInterface dialog) {
        dialog.dismiss();
    }

}
