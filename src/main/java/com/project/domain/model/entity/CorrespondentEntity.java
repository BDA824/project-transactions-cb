package com.project.domain.model.entity;

import com.project.domain.model.enums.CorrespondentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CorrespondentEntity {

    private int codeCB;

    private int id;

    private int idCustomer;

    private double approvedAmount;

    private int averageTrx;

    private String location;

    private String type;

    private String owner;

    private double lastClosed;

    private CorrespondentStatus state;

    public CorrespondentEntity() {
        this.state = CorrespondentStatus.ACTIVE;
    }

    public void assignState() {this.state = CorrespondentStatus.ACTIVE; }
}
