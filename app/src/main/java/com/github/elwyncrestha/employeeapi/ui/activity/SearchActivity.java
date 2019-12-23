package com.github.elwyncrestha.employeeapi.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.elwyncrestha.employeeapi.R;
import com.github.elwyncrestha.employeeapi.api.EmployeeApi;
import com.github.elwyncrestha.employeeapi.model.Employee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private EditText etEmployeeId;
    private Button btnSearch;
    private TextView tvSearchDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.etEmployeeId = findViewById(R.id.etEmployeeId);
        this.btnSearch = findViewById(R.id.btnSearch);
        this.tvSearchDetail = findViewById(R.id.tvSearchDetail);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EmployeeApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.btnSearch.setOnClickListener(v -> {
            final EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);
            Call<Employee> employeeCall = employeeApi.findOne(Integer.parseInt(etEmployeeId.getText().toString()));
            employeeCall.enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(Call<Employee> call, Response<Employee> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(SearchActivity.this, "Error " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Employee employee = response.body();
                    String string = "------------------\n" +
                            "Id: " + employee.getId() +
                            "Name: " + employee.getName() +
                            "Salary: " + employee.getSalary() +
                            "Age: " + employee.getAge() +
                            "\n";
                    tvSearchDetail.setText(string);
                }

                @Override
                public void onFailure(Call<Employee> call, Throwable t) {
                    Log.d("Failed to load employee", "onFailure: " + t.getLocalizedMessage());
                    Toast.makeText(SearchActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
