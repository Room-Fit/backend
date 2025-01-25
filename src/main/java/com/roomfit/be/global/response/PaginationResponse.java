package com.roomfit.be.global.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> data;
    private long totalCount;
    private boolean hasNext;
}
