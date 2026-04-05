package com.project.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class correspondentEntity {

    private int code_cb;

    private int idCustomer;

    private double approved_amount;

    private int average_trx;

    private String location;

    private String type;

    private String owner;

    private double last_clousure;

    private CorrespondentStatus state;

    public correspondentEntity() {
        this.state = CorrespondentStatus.ACTIVE;
    }
}
