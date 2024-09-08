package com.example.backendcoreservice.api.pagination;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaginationRequest<T> extends Page {
    private T criteria;

}
