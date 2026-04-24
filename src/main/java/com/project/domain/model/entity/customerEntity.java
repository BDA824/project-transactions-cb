package com.project.domain.model.entity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize
public class customerEntity {

    private int identification;
    private int age;
    private LocalDate date_vinculation;
    private Long phone;
    private String address;
}