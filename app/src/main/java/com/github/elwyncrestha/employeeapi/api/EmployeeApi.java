package com.github.elwyncrestha.employeeapi.api;

import com.github.elwyncrestha.employeeapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeApi {

    String URL = "http://dummy.restapiexample.com/api/v1/";

    @GET("employees")
    Call<List<Employee>> findAll();

    @GET("employee/{id}")
    Call<Employee> findOne(@Path("id") long id);

    @POST("create")
    Call<Void> save(@Body Employee employee);
}
