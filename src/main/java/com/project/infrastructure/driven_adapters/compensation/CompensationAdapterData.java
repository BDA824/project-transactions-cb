package com.project.infrastructure.driven_adapters.compensation;

import com.project.domain.model.enums.CompensationStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.time.LocalDate;

@Data
@Table("compensation")
public class CompensationAdapterData implements Persistable<Integer> {

    @Id
    @Column("id_compensation")
    private int idCompensation;

    @Column("code_cb")
    private int codeCB;

    @Column("date_limit")
    private LocalDate dateLimit;

    @Column("total_value")
    private double totalValue;

    @Column("remaining_value")
    private double remainingValue;


    @Column("state")
    private String state;

    @Override
    public Integer getId() {
        return idCompensation;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
