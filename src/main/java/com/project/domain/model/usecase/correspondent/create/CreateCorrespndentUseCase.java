package com.project.domain.model.usecase.correspondent.create;

import com.project.domain.model.gateway.ICompensationRepository;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.entity.compensationEntity;
import com.project.domain.model.entity.correspondentEntity;
import com.project.domain.model.gateway.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class CreateCorrespndentUseCase {

    private final ICorrespondentRepository correspondentRepository;
    private final ICompensationRepository cmpRepository;
    private final ICustomerRepository customerRepository;

    public Mono<correspondentEntity> createCB(correspondentEntity cb) {

        return customerRepository.findCustomerById(cb.getIdCustomer())
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Customer: " + cb.getIdCustomer() + " not found.")
                ))
                .flatMap(cl -> {
                    cb.assignState();
                    return correspondentRepository.create(cb).flatMap(correspondentSave -> createCompensation(correspondentSave)
                            .thenReturn(correspondentSave));
                });
    }

    private Mono<Void> createCompensation(correspondentEntity cb){
        compensationEntity cmp = new compensationEntity();
        int code_cmp = ThreadLocalRandom.current().nextInt(1001, 9999);

        cmp = new compensationEntity(
                code_cmp,
                cb.getCode_cb(),
                cmp.setDate_cmp(),
                cb.getLast_clousure(),
                cb.getLast_clousure(),
                cmp.getState()
        );

        return cmpRepository.create(cmp);
    }


}
