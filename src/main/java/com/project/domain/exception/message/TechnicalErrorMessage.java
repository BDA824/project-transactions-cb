package com.project.domain.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechnicalErrorMessage {

    CUSTOMER_SAVE(
            "CSA001", "Customer already exist"
    ),
    CUSTOMER_FIND(
            "CSA002", "Customer not found"
    ),
    CORRESPONDENT_SAVE(
            "CA0010", "Correspondent already exist"
    ),
    CORRESPONDENT_FIND(
            "CA0011", "Correspondent not found"
    ),
    COMPENSATION_FIND(
            "CMP0021", "Compensation not found"
    );


    private final String code;
    private final String description;
}
