package com.twa.flights.api.clusters.dto.response;

public class PaginationDTO {

    private Integer offset;
    private Integer limit;
    private Integer total;

    public PaginationDTO() {
    }

    public PaginationDTO(Integer offset, Integer limit, Integer total) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
