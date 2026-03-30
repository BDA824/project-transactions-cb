package com.project.domain.model;

import java.time.LocalDate;

public class transactionEntity {

    private final int id_trx;
    private final int code_cb;
    private final String entity;
    private final double amount_trx;
    private String state;
    private final LocalDate date_trx;

    public transactionEntity(int id_trx, int code_cb, String entity, double amount_trx, LocalDate date_trx) {
        this.id_trx = id_trx;
        this.code_cb = code_cb;
        this.entity = entity;
        this.amount_trx = amount_trx;
        this.state = "Pending";
        this.date_trx = date_trx;
    }

    public int getId_trx() {
        return id_trx;
    }

    public int getCode_cb() {
        return code_cb;
    }

    public String getEntity() {
        return entity;
    }

    public double getAmount_trx() {
        return amount_trx;
    }

    public String getState() {
        return state;
    }

    public LocalDate getDate_trx() {
        return date_trx;
    }
}
