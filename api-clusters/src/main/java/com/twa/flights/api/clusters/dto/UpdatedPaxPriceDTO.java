package com.twa.flights.api.clusters.dto;

import java.math.BigDecimal;

import com.twa.flights.common.dto.itinerary.MarkupDTO;

public class UpdatedPaxPriceDTO {

    private MarkupDTO markup;
    private BigDecimal total;

    public MarkupDTO getMarkup() {
        return markup;
    }

    public void setMarkup(MarkupDTO markup) {
        this.markup = markup;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
