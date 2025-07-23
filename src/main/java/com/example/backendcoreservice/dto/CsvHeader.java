package com.example.backendcoreservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CsvHeader {
    private String header;
    private Boolean required;


}