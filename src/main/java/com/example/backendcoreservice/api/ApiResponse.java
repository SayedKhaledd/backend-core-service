package com.example.backendcoreservice.api;

import lombok.Builder;

@Builder
public record ApiResponse<T>(Integer appCode, T response, String message, Boolean success, String error) {
}
