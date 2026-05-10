package com.project.domain.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.domain.model.enums.CompensationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonDeserialize
public class compensationEntity {

    private int id_compensation;

    private int code_cb;

    private LocalDate date_limit;

    private double total_value;

    private double remaining_value;

    private CompensationStatus state;

    public compensationEntity() {
        this.state = CompensationStatus.PENDING;
    }

    public LocalDate setDate_cmp() {
        LocalDate date_limit = LocalDate.now();

        if (LocalDate.now().getDayOfMonth() >= 18)
        {
            date_limit = LocalDate.now().plusMonths(1);
            date_limit = date_limit.withDayOfMonth(18);
        }
        else {
            date_limit = date_limit.withDayOfMonth(18);
        }

        return date_limit;
    }

    @Override
    public String toString() {
        return "compensationEntity {" +
                "id_compensation = " + id_compensation +
                ", code_cb = " + code_cb +
                ", date_limit = " + date_limit +
                ", total_value = " + total_value +
                ", remaining_value = " + remaining_value +
                ", state='" + state + '\'' +
                '}';
    }
}
