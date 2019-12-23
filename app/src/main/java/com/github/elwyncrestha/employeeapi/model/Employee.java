package com.github.elwyncrestha.employeeapi.model;

import androidx.annotation.Nullable;

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
    @Nullable
    private Long id;

    @SerializedName(value = "name", alternate = {"employee_name"})
    private String name;

    @SerializedName(value = "salary", alternate = {"employee_salary"})
    private float salary;

    @SerializedName(value = "age", alternate = {"employee_age"})
    private int age;

    @SerializedName(value = "profile_image")
    private String profileImage;
}
