package com.robson.dto;

import java.util.List;

public class PagedResponseDto<T> {
    public List<T> data;
    public int page;
    public int size;
    public long total;
    public int totalPages;

    public PagedResponseDto() {
    }

    public PagedResponseDto(List<T> data, int page, int size, long total, int totalPages) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.total = total;
        this.totalPages = totalPages;
    }
}
