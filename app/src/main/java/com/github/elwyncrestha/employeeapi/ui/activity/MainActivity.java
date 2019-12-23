package com.github.elwyncrestha.employeeapi.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.elwyncrestha.employeeapi.R;
import com.github.elwyncrestha.employeeapi.api.EmployeeApi;
import com.github.elwyncrestha.employeeapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.tvOutput);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EmployeeApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);
        Call<List<Employee>> listCall = employeeApi.findAll();
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Error " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Employee> employeeList = response.body();
                employeeList.forEach(employee -> {
                    String string = "------------------\n" +
                            "Id: " + employee.getId() +
                            "Name: " + employee.getName() +
                            "Salary: " + employee.getSalary() +
                            "Age: " + employee.getAge() +
                            "\n";
                    tvOutput.append(string);
                });
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Failed to load employee", "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
