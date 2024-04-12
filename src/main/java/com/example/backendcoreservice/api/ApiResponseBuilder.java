package com.example.backendcoreservice.api;

import com.example.backendcoreservice.dto.AbstractDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiResponseBuilder<T extends AbstractDto> {

    public ApiResponse<T> buildSuccessResponse(T data) {
        return ApiResponse.<T>builder()
                .appCode(200)
                .response(data)
                .message("success")
                .success(true)
                .error(null)
                .build();
    }

    public ApiResponse<List<T>> buildSuccessResponse(List<T> data) {
        return ApiResponse.<List<T>>builder()
                .appCode(200)
                .response(data)
                .message("success")
                .success(true)
                .error(null)
                .build();
    }


    public ApiResponse<T> buildFailureResponse(String error, Integer appCode) {
        return ApiResponse.<T>builder()
                .appCode(appCode)
                .response(null)
                .message("failure")
                .success(false)
                .error(error)
                .build();
    }


}
