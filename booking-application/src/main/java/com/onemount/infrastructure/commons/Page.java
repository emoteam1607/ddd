package com.onemount.infrastructure.commons;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * author: anct
 * date: 10/09/2022
 * YNWA
 */
@Getter
@Setter
public class Page<E> {
    private long total;
    private int pageIndex;
    private int pageSize;
    private List<E> data;

    public Page(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Page(long total,
                int pageIndex,
                int pageSize,
                List<E> data) {
        this.total = total;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.data = data;
    }
}
