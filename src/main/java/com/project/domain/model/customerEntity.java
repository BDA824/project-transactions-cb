package com.project.domain.model;
import java.time.*;

public class customerEntity {

    private final int identification;
    private int age;
    private final LocalDate date_vinculation;
    private int phone;
    private String address;

    public customerEntity(int identification, int age, int phone, String address) {
        this.identification = identification;
        this.age = age;
        this.date_vinculation = LocalDate.now();
        this.phone = phone;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public int getIdentification() {
        return identification;
    }

    public LocalDate getDate_vinculation() {
        return date_vinculation;
    }

    public int getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}