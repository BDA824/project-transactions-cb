package com.project.domain.model.usecase.compensation.get;

import com.project.domain.model.entity.compensationEntity;
import com.project.domain.model.gateway.ICompensationRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllCompensationsUseCase {

    private final ICompensationRepository compensationRepository;

    public Flux<compensationEntity> getAllCompensations(){
        return compensationRepository.getAllCompensations();
    }

}
