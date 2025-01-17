package com.example.backendcoreservice.api;

import com.example.backendcoreservice.api.pagination.PaginationResponse;
import com.example.backendcoreservice.dto.AbstractDto;
import com.example.backendcoreservice.dto.Dto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiResponseBuilder<DTO extends Dto> {

    public ApiResponse<DTO> buildSuccessResponse(DTO data) {
        return ApiResponse.<DTO>builder()
                .appCode(200)
                .response(data)
                .message("success")
                .success(true)
                .error(null)
                .build();
    }

    public ApiResponse<List<DTO>> buildSuccessResponse(List<DTO> data) {
        return ApiResponse.<List<DTO>>builder()
                .appCode(200)
                .response(data)
                .message("success")
                .success(true)
                .error(null)
                .build();
    }

    public ApiResponse<PaginationResponse<DTO>> buildSuccessResponse(PaginationResponse<DTO> data) {
        return ApiResponse.<PaginationResponse<DTO>>builder()
                .appCode(200)
                .response(data)
                .message("success")
                .success(true)
                .error(null)
                .build();
    }

    public ApiResponse<?> buildSuccessResponse() {
        return ApiResponse.<PaginationResponse<DTO>>builder()
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
