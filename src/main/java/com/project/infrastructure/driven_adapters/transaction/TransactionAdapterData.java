package com.project.infrastructure.driven_adapters.transaction;

import com.project.domain.model.enums.TransactionStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.time.LocalDate;

@Data
@Table("transaction")
public class TransactionAdapterData implements Persistable<Integer> {

    @Id
    @Column("id_trx")
    private Integer id_trx;

    @Column("code_cb")
    private int code_cb;

    @Column("entity")
    private String entity;

    @Column("amount_trx")
    private double amount_trx;

    @Column("state")
    private TransactionStatus state;

    @Column("date_trx")
    private LocalDate date_trx;

    @Override
    public Integer getId() {
        return id_trx;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
