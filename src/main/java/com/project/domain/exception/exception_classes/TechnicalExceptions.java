package com.project.domain.exception.exception_classes;

import com.project.domain.exception.message.TechnicalErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TechnicalExceptions extends RuntimeException{

    private final TechnicalErrorMessage technicalErrorMessage;

    public TechnicalExceptions(Throwable cause, TechnicalErrorMessage technicalErrorMessage) {
        super(cause);
        this.technicalErrorMessage = technicalErrorMessage;
    }
}
