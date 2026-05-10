package com.project.domain.model.usecase.correspondent.get;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.exception.message.BusinessErrorMessage;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.entity.correspondentEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetCorrespondentByCodeCbUseCase {

    private final ICorrespondentRepository correspondentRepository;

    public Mono<correspondentEntity> getCorrespondentById(int code_cb) {
        return correspondentRepository
                .getCorrespondentById(code_cb)
                .switchIfEmpty(Mono.error(
                        new BusinessException(BusinessErrorMessage.CORRESPONDENT_NOT_FOUND)
                ));
    }

}
