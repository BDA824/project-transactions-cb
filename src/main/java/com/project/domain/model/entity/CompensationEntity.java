package com.project.domain.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.domain.model.enums.CompensationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonDeserialize
@Builder
public class CompensationEntity {

    private int codeCB;

    private LocalDate dateLimit;

    private double totalValue;

    private double remainingValue;

    private CompensationStatus state;

    public CompensationEntity() {
        this.state = CompensationStatus.PENDING;
    }

    public LocalDate setDate_cmp(LocalDate dateLimit) {
        LocalDate date = dateLimit;

        if (LocalDate.now().getDayOfMonth() >= 18)
        {
            date = LocalDate.now().plusMonths(1);
            date = date.withDayOfMonth(18);
        }
        else {
            date = date.withDayOfMonth(18);
        }

        return date;
    }

}
