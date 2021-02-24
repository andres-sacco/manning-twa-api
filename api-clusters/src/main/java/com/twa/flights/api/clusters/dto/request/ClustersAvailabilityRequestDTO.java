package com.twa.flights.api.clusters.dto.request;

import java.util.Objects;

import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

public class ClustersAvailabilityRequestDTO extends AvailabilityRequestDTO {

    private String id;
    private Integer offset;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ClustersAvailabilityRequestDTO that = (ClustersAvailabilityRequestDTO) o;
        return Objects.equals(getFrom(), that.getFrom()) && Objects.equals(getTo(), that.getTo())
                && Objects.equals(getDeparture(), that.getDeparture()) && Objects.equals(getAdults(), that.getAdults())
                && Objects.equals(getChildren(), that.getChildren()) && Objects.equals(getInfants(), that.getInfants())
                && Objects.equals(getAmount(), that.getAmount()) && Objects.equals(id, that.id)
                && Objects.equals(offset, that.offset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getDeparture(), getAdults(), getChildren(), getInfants(), getAmount(),
                id, offset);
    }

    @Override
    public String toString() {
        return "ClustersAvailabilityRequestDTO{" + "from='" + getFrom() + '\'' + ", to='" + getTo() + '\''
                + ", departure='" + getDeparture() + '\'' + ", adults=" + getAdults() + ", children=" + getChildren()
                + ", infants=" + getInfants() + ", amount=" + getAmount() + ", id=" + getId() + ", offset="
                + getOffset() + '}';
    }

}
