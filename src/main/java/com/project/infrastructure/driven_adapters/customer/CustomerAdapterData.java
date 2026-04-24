package com.project.infrastructure.driven_adapters.customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.time.LocalDate;

@Data
@Table("customer")
@Builder
public class CustomerAdapterData implements Persistable<Integer> {
    
    @Id
    @Column("id")
    private Integer identification;
    
    @Column("age")
    private int age;

    @Column("date_vinculation")
    private LocalDate date_vinculation;

    @Column("phone")
    private Long phone;

    @Column("address")
    private String address;


    @Override
    public Integer getId() {
        return identification;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
