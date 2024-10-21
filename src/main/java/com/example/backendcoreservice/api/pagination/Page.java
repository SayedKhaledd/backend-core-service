package com.example.backendcoreservice.api.pagination;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private Integer pageNumber;
    private Integer pageSize;
}
