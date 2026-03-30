package com.project.domain.model;

import java.time.LocalDate;

public class compensationEntity {

    private final int id_compensation;
    private final int code_cb;
    private LocalDate date_limit;
    private double total_value;
    private double remaining_value;
    private String state;

    public compensationEntity(int id_compensation, int code_cb, double total_value, double remaining_value) {
        this.id_compensation = id_compensation;
        this.code_cb = code_cb;
        this.date_limit = setDate_cmp();
        this.total_value = total_value;
        this.remaining_value = remaining_value;
        this.state = "Pending";
    }

    public int getId_compensation() {
        return id_compensation;
    }

    public int getCode_cb() {
        return code_cb;
    }

    public LocalDate getDate_limit() {
        return date_limit;
    }

    public double getTotal_value() {
        return total_value;
    }

    public double getRemaining_value() {
        return remaining_value;
    }

    public String getState() {
        return state;
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

    public static void main(String[] args) {
        compensationEntity cmp = new compensationEntity(1546, 87052, 2550000, 2550000);
        System.out.println(cmp.toString());
    }
}
