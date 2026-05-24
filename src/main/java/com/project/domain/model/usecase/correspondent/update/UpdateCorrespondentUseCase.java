package com.project.domain.model.usecase.correspondent.update;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.exception.message.BusinessErrorMessage;
import com.project.domain.model.entity.CompensationEntity;
import com.project.domain.model.gateway.ICompensationRepository;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.entity.CorrespondentEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateCorrespondentUseCase {

    public final ICorrespondentRepository correspondentRepository;
    public final ICompensationRepository compensationRepository;

    public Mono<CorrespondentEntity> updateCorrespondent(CorrespondentEntity cb) {

        return correspondentRepository
                .updateCorrespondent(cb)
                .flatMap(corr -> updateLastCl(
                        corr.getLastClosed(),
                        corr.getCodeCB())
                        .thenReturn(corr))
                .switchIfEmpty(Mono.error(
                        new BusinessException(BusinessErrorMessage.CORRESPONDENT_NOT_FOUND)
                ));
    }

    private Mono<CompensationEntity> updateLastCl(double value_last, int code_cb) {

        return compensationRepository
                .updateClosed(value_last, code_cb);

    }

}
