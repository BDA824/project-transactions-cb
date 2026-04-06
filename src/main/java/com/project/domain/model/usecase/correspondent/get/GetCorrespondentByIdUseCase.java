package com.project.domain.model.usecase.correspondent.get;

import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.entity.correspondentEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetCorrespondentByIdUseCase {

    private final ICorrespondentRepository correspondentRepository;

    public Mono<correspondentEntity> getCorrespondentById(int code_cb) {
        return correspondentRepository.getCorrespondentById(code_cb);
    }

}
