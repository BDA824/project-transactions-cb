package com.project.domain.model.usecase.correspondent.get;

import com.project.domain.model.entity.CorrespondentEntity;
import com.project.domain.model.gateway.ICorrespondentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllCorrespondentUseCase {

    private final ICorrespondentRepository correspondentRepository;

    public Flux<CorrespondentEntity> getAllCorrespondents() {
        return correspondentRepository.getAllCorrespondents();
    }

}
