package com.github.elwyncrestha.employeeapi.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.elwyncrestha.employeeapi.R;
import com.github.elwyncrestha.employeeapi.api.EmployeeApi;
import com.github.elwyncrestha.employeeapi.model.Employee;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity {

    private EditText etAddName, etAddSalary, etAddAge;
    private Button btnAddEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.etAddName = findViewById(R.id.etAddName);
        this.etAddSalary = findViewById(R.id.etAddSalary);
        this.etAddAge = findViewById(R.id.etAddAge);
        this.btnAddEmployee = findViewById(R.id.btnAddEmployee);

        this.btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(EmployeeApi.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);
                Employee employee = new Employee(
                        null,
                        etAddName.getText().toString(),
                        Float.parseFloat(etAddSalary.getText().toString()),
                        Integer.parseInt(etAddAge.getText().toString()),
                        null);
                Call<Void> saveCall = employeeApi.save(employee);
                Gson gson = new Gson();

                Log.e("Employee Error", gson.toJson(employee), null);
                saveCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() != 200) {
                            Toast.makeText(AddActivity.this, "Error saving employee!!!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(AddActivity.this, "Successfully saved!!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AddActivity.this, "Failed to save!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
