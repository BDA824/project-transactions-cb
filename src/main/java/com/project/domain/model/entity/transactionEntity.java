package com.project.domain.model.entity;

import com.project.domain.model.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class transactionEntity {

    private int id_trx;

    private int code_cb;

    private String entity;

    private double amount_trx;

    private TransactionStatus state;

    private LocalDate date_trx;

    public transactionEntity () {
        this.state = TransactionStatus.PENDING;
    }

}