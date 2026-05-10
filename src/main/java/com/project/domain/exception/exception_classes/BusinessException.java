package com.project.domain.exception.exception_classes;

import com.project.domain.exception.message.BusinessErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException{

    private final BusinessErrorMessage businessErrorMessage;
}
