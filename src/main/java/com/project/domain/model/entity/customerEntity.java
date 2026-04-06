package com.project.domain.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class customerEntity {

    private int identification;
    private int age;
    private LocalDate date_vinculation;
    private int phone;
    private String address;
}