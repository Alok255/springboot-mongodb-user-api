package com.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDTO {
    /*
    {
      "id": 20,
      "employee_name": "Dai Rios",
      "employee_salary": "217500",
      "employee_age": "35",
      "profile_image": ""
    }
     */
    private int id;
    @JsonProperty(value = "employee_name")
    private String employeeName;
    @JsonProperty(value="employee_age")
    private String employeeAge;
    @JsonProperty(value="employee_salary")
    private String employeeSalary;
}
