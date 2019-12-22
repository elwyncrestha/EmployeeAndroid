package com.github.elwyncrestha.employeeapi.api;

import com.github.elwyncrestha.employeeapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApi {

    String URL = "http://dummy.restapiexample.com/api/v1/";

    @GET("employees")
    Call<List<Employee>> findAll();
}
