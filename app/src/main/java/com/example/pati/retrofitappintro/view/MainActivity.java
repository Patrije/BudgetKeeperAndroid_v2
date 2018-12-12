package com.example.pati.retrofitappintro.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pati.retrofitappintro.R;
import com.example.pati.retrofitappintro.model.Transaction;
import com.example.pati.retrofitappintro.util.TimeHelper;


import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView budget;
    // private EmployeeRestApi employeeRestApi;
    private EditText loginEdit, balanceEdit;
    private Button askButton, expenseButton, incomeButton, historyButton;
    private TransactionViewModel transactionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askButton = (Button) findViewById(R.id.ask_button);
        historyButton = (Button) findViewById(R.id.history_button);
        budget = (TextView) findViewById(R.id.budget);
        expenseButton = (Button) findViewById(R.id.expense_button);
        incomeButton = (Button) findViewById(R.id.income_button);
        try {
            transactionViewModel = new TransactionViewModel(this.getApplication());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Dialog dialog = new Dialog(this, transactionViewModel);
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                setSum();
            }

        });
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                setSum();
            }

        });
        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                setSum();
            }

        });

        Double sum;
        if (transactionViewModel.getTransactionSum() != null) {
            sum = transactionViewModel.getTransactionSum();
        } else {
            sum = 0D;
        }
        budget.setText(sum.toString());
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

    @Override
    protected void onResume() {
        super.onResume();
        setSum();
    }

double userinput;

    public void showDialog() {

        final android.app.Dialog dialog = new android.app.Dialog(this);
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

        transactionViewModel.insert(new Transaction(value, TimeHelper.getNow().getTimeInMillis(),23L,category));
        Double sum;
        if (budget.getText().toString() != null) {
            sum = Double.parseDouble(budget.getText().toString())+value;
        } else {
            sum = 0D;
        }
        budget.setText(sum.toString());
        dialog.dismiss();
    }

    private void onDismissed(final DialogInterface dialog) {
        dialog.dismiss();
    }
}
        //     budgetTextView = (TextView) findViewById(R.id.budget);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.103:8080/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

       // employeeRestApi = retrofit.create(EmployeeRestApi.class);
//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createEmployee();
//                //  getAllEmployees();
//            }
//        });


//    private void getAllEmployees() {
//        Call<List<Employee>> call = employeeRestApi.getAllEmployees();
//
//        call.enqueue(new Callback<List<Employee>>() {
//            @Override
//            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
//
//                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
//                    return;
//                }
//                List<Employee> employees = response.body();
//
//                for (Employee employee : employees) {
//                    String content = "";
//                    content += employee.getId() + employee.getName() + employee.getSurname() +
//                            employee.getLogin() + employee.getPosition() + employee.getPassword() +
//                            employee.getEmail() + employee.getBalanace();
//                    textViewResult.append(content);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Employee>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
//            }
//        });
//    }
//
//    private void createEmployee() {
//        Employee employee = new Employee("markus", "Gora", loginEdit.getText().toString(), "stolarz", "mojpassword", "ewgwe//@gmail.com", Double.parseDouble(balanceEdit.getText().toString()));
//        Call<Employee> call = employeeRestApi.createEmployee(employee);
//        call.enqueue(new Callback<Employee>() {
//            @Override
//            public void onResponse(Call<Employee> call, Response<Employee> response) {
//                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
//                    return;
//                }
//                Employee employeeRespone = response.body();
//                String content = "";
//                content += "Code:" + response.code() + employeeRespone.getBalanace() + employeeRespone.getPassword() + employeeRespone.getPosition() + employeeRespone.getLogin() + employeeRespone.getEmail() + employeeRespone.getSurname() + employeeRespone.getName() + employeeRespone.getId();
//                textViewResult.setText(content);
//
//            }
//
//            @Override
//            public void onFailure(Call<Employee> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
//            }
//        });
//    }

