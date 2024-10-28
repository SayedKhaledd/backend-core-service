package com.example.backendcoreservice.api;

import com.example.backendcoreservice.api.pagination.PaginationResponse;
import com.example.backendcoreservice.dto.AbstractDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiResponseBuilder<Dto extends AbstractDto> {

    public ApiResponse<Dto> buildSuccessResponse(Dto data) {
        return ApiResponse.<Dto>builder()
                .appCode(200)
                .response(data)
                .message("success")
                .success(true)
                .error(null)
                .build();
    }

    public ApiResponse<List<Dto>> buildSuccessResponse(List<Dto> data) {
        return ApiResponse.<List<Dto>>builder()
                .appCode(200)
                .response(data)
                .message("success")
                .success(true)
                .error(null)
                .build();
    }

    public ApiResponse<PaginationResponse<Dto>> buildSuccessResponse(PaginationResponse<Dto> data) {
        return ApiResponse.<PaginationResponse<Dto>>builder()
                .appCode(200)
                .response(data)
                .message("success")
                .success(true)
                .error(null)
                .build();
    }

    public ApiResponse<?> buildSuccessResponse() {
        return ApiResponse.<PaginationResponse<Dto>>builder()
                .appCode(200)
                .response(null)
                .message("success")
                .success(true)
                .error(null)
                .build();
    }

    public ApiResponse<List<String>> buildSuccessStringResponse(List<String> data) {
        return ApiResponse.<List<String>>builder()
                .appCode(200)
                .response(data)
                .message("success")
                .success(true)
                .error(null)
                .build();
    }


    public ApiResponse buildFailureResponse(String error, Integer appCode) {
        return ApiResponse.builder()
                .appCode(appCode)
                .response(null)
                .message("failure")
                .success(false)
                .error(error)
                .build();
    }


}
