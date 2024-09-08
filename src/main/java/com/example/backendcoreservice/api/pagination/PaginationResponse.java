package com.example.backendcoreservice.api.pagination;

import com.example.backendcoreservice.dto.AbstractDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder(toBuilder = true, builderMethodName = "paginationResponseBuilder")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse<T extends AbstractDto> extends Page {
    private Long totalNumberOfPages;
    private Long totalNumberOfElements;
    private List<T> result;
    private Boolean isFirst;
    private Boolean isLast;

}
