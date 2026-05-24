package com.project.domain.model.usecase.compensation.get;

import com.project.domain.model.entity.CompensationEntity;
import com.project.domain.model.gateway.ICompensationRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllCompensationsUseCase {

    private final ICompensationRepository compensationRepository;

    public Flux<CompensationEntity> getAllCompensations(){
        return compensationRepository.getAllCompensations();
    }

}
