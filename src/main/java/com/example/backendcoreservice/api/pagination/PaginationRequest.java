package com.example.backendcoreservice.api.pagination;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaginationRequest<T> extends Page {
    private T criteria;
    @NotNull(message = "this field is required")
    Boolean deletedRecords = false;

}
