package com.project.domain.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessErrorMessage {

    CUSTOMER_ALREADY_EXIST(
            "CUSTOMER_ALREADY_EXIST", "This customer is already exist"
    ),
    CUSTOMER_NOT_FOUND(
            "CUSTOMER_NOT_FOUND", "The customer isn't registered"
    ),
    CORRESPONDENT_ALREADY_EXIST(
            "CORRESPONDENT_ALREADY_EXIST", "The correspondent with this code cb already exist"
    ),
    CORRESPONDENT_NOT_FOUND(
            "CORRESPONDENT_NOT_FOUND", "The correspondent with code cb doesn't exist"
    ),
    COMPENSATION_NOT_FOUND(
            "COMPENSATION_NOT_FOUND", "The correspondent hasn't outstanding compensation"
    );

    private final String type;
    private final String description;
}
