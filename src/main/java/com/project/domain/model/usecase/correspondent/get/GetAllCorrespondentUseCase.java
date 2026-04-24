package com.project.domain.model.usecase.correspondent.get;

import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.entity.correspondentEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class GetAllCorrespondentUseCase {

    private final ICorrespondentRepository correspondentRepository;

    public Flux<correspondentEntity> getAllCorrespondents() {
        return correspondentRepository.getAllCorrespondents();
    }

}
