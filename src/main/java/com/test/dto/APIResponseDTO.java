package com.test.dto;

import lombok.Data;

@Data
public class APIResponseDTO<T> {
    private String status;
    private String message;
    private T data;
}
