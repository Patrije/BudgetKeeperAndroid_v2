package com.example.pati.retrofitappintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult=findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.103:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Employee>> call = jsonPlaceHolderApi.getAllEmployees();

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
                            employee.getNickname()+employee.getPosition()+employee.getPassword()+
                            employee.getIncome()+employee.getExpenditure();
                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
