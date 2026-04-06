package com.project.domain.model.usecase.correspondent.get;

import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.entity.correspondentEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class GetAllCorrespondentUseCase {

    private final ICorrespondentRepository correspondentRepository;

    public Mono<List<correspondentEntity>> getAllCorrespondents() {
        return correspondentRepository.getAllCorrespondents();
    }

}
