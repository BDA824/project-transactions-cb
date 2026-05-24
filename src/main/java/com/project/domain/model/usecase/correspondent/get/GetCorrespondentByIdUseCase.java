package com.project.domain.model.usecase.correspondent.get;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.exception.message.BusinessErrorMessage;
import com.project.domain.model.entity.CorrespondentEntity;
import com.project.domain.model.gateway.ICorrespondentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetCorrespondentByIdUseCase {

    private final ICorrespondentRepository correspondentRepository;

    public Flux<CorrespondentEntity> getCorrespondentByIdCustomer(int identification) {

        return correspondentRepository
                .findCorrespondentById(identification)
                .switchIfEmpty(Mono.error(
                        new BusinessException(BusinessErrorMessage.CORRESPONDENT_NOT_FOUND)
                ));

    }

}
