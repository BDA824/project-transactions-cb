package com.project.domain.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.domain.model.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonDeserialize
public class TransactionEntity {

    private int idTrx;

    private int codeCB;

    private String entity;

    private double amountTrx;

    private TransactionStatus state;

    private LocalDate dateTrx;

    public TransactionEntity() {
        this.state = TransactionStatus.PENDING;
    }

}