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
    private Integer id_compensation;

    @Column("code_cb")
    private int code_cb;

    @Column("date_limit")
    private LocalDate date_limit;

    @Column("total_value")
    private double total_value;

    @Column("remaining_value")
    private double remaining_value;

    @Column("state")
    private CompensationStatus state;

    @Override
    public Integer getId() {
        return id_compensation;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
