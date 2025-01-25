package com.roomfit.be.global.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationMeta {
    private long totalCount;
    private boolean hasNext;

    public static PaginationMeta of(long totalCount, boolean hasNext) {
        return new PaginationMeta(totalCount, hasNext);
    }
}
