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
    private Integer idTrx;

    @Column("code_cb")
    private int codeCB;

    @Column("entity")
    private String entity;

    @Column("amount_trx")
    private double amountTrx;

    @Column("state")
    private TransactionStatus state;

    @Column("date_trx")
    private LocalDate dateTrx;

    @Override
    public Integer getId() {
        return idTrx;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
