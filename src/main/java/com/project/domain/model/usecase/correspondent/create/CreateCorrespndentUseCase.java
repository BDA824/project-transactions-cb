package com.project.domain.model.usecase.correspondent.create;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.exception.message.BusinessErrorMessage;
import com.project.domain.model.entity.customerEntity;
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

        return validateCustomerExists(cb.getIdCustomer())
                .then(validateCorrespondentNotExists(cb.getCode_cb()))
                .then(correspondentRepository.create(cb))
                .flatMap(saved ->
                        createCompensation(saved)
                                .thenReturn(saved)
                );
    }

    private Mono<customerEntity> validateCustomerExists(int idCustomer) {
        return customerRepository.findCustomerById(idCustomer)
                .switchIfEmpty(Mono.error(
                        new BusinessException(
                                BusinessErrorMessage.CUSTOMER_NOT_FOUND
                        )
                ));
    }

    private Mono<Void> validateCorrespondentNotExists(int codeCb) {
        return correspondentRepository.getCorrespondentById(codeCb)
                .flatMap(existing ->
                        Mono.error(
                                new BusinessException(
                                        BusinessErrorMessage.CORRESPONDENT_ALREADY_EXIST
                                )
                        )
                )
                .switchIfEmpty(Mono.empty())  // ← no existe, puede continuar
                .then();
    }

    private Mono<compensationEntity> createCompensation(correspondentEntity cb){
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
