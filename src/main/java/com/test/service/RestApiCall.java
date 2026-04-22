package com.test.service;

import com.test.dto.APIResponseDTO;
import com.test.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "RestApiClient", url = "https://dummy.restapiexample.com/api/v1")
public interface RestApiCall {
    @GetMapping("/employees")
    APIResponseDTO<List<EmployeeDTO>> getAllEmployees();
}
