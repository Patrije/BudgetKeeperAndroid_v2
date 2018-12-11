package com.example.pati.retrofitappintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private EmployeeRestApi employeeRestApi;
    private EditText loginEdit, balanceEdit;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEdit=(EditText) findViewById(R.id.login);
        balanceEdit=(EditText) findViewById(R.id.balance);
        textViewResult=findViewById(R.id.text_view_result);
        sendButton = (Button) findViewById(R.id.sendButton);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.103:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        employeeRestApi = retrofit.create(EmployeeRestApi.class);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEmployee();
                //  getAllEmployees();
            }
        });


    }


private void getAllEmployees(){
    Call<List<Employee>> call = employeeRestApi.getAllEmployees();

    call.enqueue(new Callback<List<Employee>>() {
        @Override
        public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

            if(!response.isSuccessful()){
                textViewResult.setText("Code: " + response.code());
                return;
            }
            List<Employee> employees = response.body();

            for(Employee employee: employees){
                String content ="";
                content +=employee.getId()+employee.getName()+employee.getSurname()+
                        employee.getLogin()+employee.getPosition()+employee.getPassword()+
                        employee.getEmail()+employee.getBalanace();
                textViewResult.append(content);
            }

        }

        @Override
        public void onFailure(Call<List<Employee>> call, Throwable t) {
            textViewResult.setText(t.getMessage());
        }
    });
}
    private void createEmployee(){
        Employee employee = new Employee("markus","Gora",loginEdit.getText().toString(),"stolarz","mojpassword","ewgwe//@gmail.com",Double.parseDouble(balanceEdit.getText().toString()));
        Call<Employee> call= employeeRestApi.createEmployee(employee);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Employee employeeRespone =response.body();
                String content ="";
                content +="Code:"+response.code()+employeeRespone.getBalanace()+employeeRespone.getPassword()+employeeRespone.getPosition()+employeeRespone.getLogin()+employeeRespone.getEmail()+employeeRespone.getSurname()+employeeRespone.getName()+employeeRespone.getId();
                textViewResult.setText(content);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
