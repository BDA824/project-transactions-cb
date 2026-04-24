package com.project.infrastructure.driven_adapters.correspondent;

import com.project.domain.model.enums.CorrespondentStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import org.springframework.data.domain.Persistable;

@Data
@Table("correspondent")
public class CorrespondentAdapterData implements Persistable<Integer> {

    @Id
    @Column("code_cb")
    private Integer code_cb;

    @Column("id_customer")
    private int idCustomer;

    @Column("approved_amount")
    private double approved_amount;

    @Column("average_trx")
    private int average_trx;

    @Column("location")
    private String location;

    @Column("type")
    private String type;

    @Column("owner")
    private String owner;

    @Column("last_clousure")
    private double last_clousure;

    @Column("state")
    private CorrespondentStatus state;

    @Override
    public boolean isNew() {
        return true;
    }

    @Override
    public Integer getId() {
        return idCustomer;
    }
}
