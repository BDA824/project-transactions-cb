package com.project.domain.model.entity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize
@Builder
public class CustomerEntity {

    private int identification;
    private int age;
    private LocalDate dateCreate;
    private Long phone;
    private String address;
}