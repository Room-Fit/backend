package com.roomfit.be.global.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResponseWithPagination<T> {
    private boolean success;
    private String message;
    private T data;
    private PaginationMeta meta;
}
