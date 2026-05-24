package com.project.domain.model.usecase.correspondent.create;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.exception.message.BusinessErrorMessage;
import com.project.domain.model.entity.CompensationEntity;
import com.project.domain.model.entity.CustomerEntity;
import com.project.domain.model.gateway.ICompensationRepository;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.entity.CorrespondentEntity;
import com.project.domain.model.gateway.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class CreateCorrespndentUseCase {

    private final ICorrespondentRepository correspondentRepository;
    private final ICompensationRepository cmpRepository;
    private final ICustomerRepository customerRepository;

    public Mono<CorrespondentEntity> createCB(CorrespondentEntity cb) {

        return validateCustomerExists(cb.getIdCustomer())
                .then(validateCorrespondentNotExists(cb.getCodeCB()))
                .then(correspondentRepository.create(cb))
                .flatMap(saved ->
                        createCompensation(saved)
                                .thenReturn(saved)
                );
    }

    private Mono<CustomerEntity> validateCustomerExists(int idCustomer) {
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

    private Mono<CompensationEntity> createCompensation(CorrespondentEntity cb){
        CompensationEntity cmp = new CompensationEntity();
        int code_cmp = ThreadLocalRandom.current().nextInt(1001, 9999);
        LocalDate date = LocalDate.now();

        cmp = new CompensationEntity(
                code_cmp,
                cb.getCodeCB(),
                cmp.setDate_cmp(date),
                cb.getLastClosed(),
                cb.getLastClosed(),
                cmp.getState()
        );

        return cmpRepository.create(cmp);
    }


}
