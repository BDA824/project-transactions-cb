package com.project.domain.model;

public class correspondentEntity {

    private final int code_cb;
    private final int idCustomer;
    private double approved_amount;
    private int average_trx;
    private String location;
    private final String type;
    private String owner;
    private double last_clousure;
    private String state;

    public correspondentEntity(int code_cb, int idCustomer, double approved_amount, int average_trx, String location, String type, double last_clousure, String owner) {
        this.code_cb = code_cb;
        this.idCustomer = idCustomer;
        this.approved_amount = approved_amount;
        this.average_trx = average_trx;
        this.location = location;
        this.type = type;
        this.last_clousure = last_clousure;
        this.owner = owner;
        this.state = "Active";
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public int getCode_cb() {
        return code_cb;
    }

    public double getApproved_amount() {
        return approved_amount;
    }

    public int getAverage_trx() {
        return average_trx;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getOwner() {
        return owner;
    }

    public double getLast_clousure() {
        return last_clousure;
    }

    public String getState() {
        return state;
    }
}
