package com.github.elwyncrestha.employeeapi.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private long id;

    @SerializedName(value = "employee_name")
    private String name;

    @SerializedName(value = "employee_salary")
    private float salary;

    @SerializedName(value = "employee_age")
    private int age;

    @SerializedName(value = "profile_image")
    private String profileImage;
}
