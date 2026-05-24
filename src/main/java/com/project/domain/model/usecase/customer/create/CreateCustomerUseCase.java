package com.project.domain.model.usecase.customer.create;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.exception.message.BusinessErrorMessage;
import com.project.domain.model.entity.CustomerEntity;
import com.project.domain.model.gateway.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public Mono<CustomerEntity> saveCustomer(CustomerEntity cl)
    {
        cl.setDateCreate(LocalDate.now());
        return validateCustomerExists(cl.getIdentification())
                .then(Mono.defer(() -> customerRepository.create(cl)));
    }

    private Mono<Void> validateCustomerExists (int identification) {

        return customerRepository
                .findCustomerById(identification)
                .flatMap(existing -> Mono.error(
                        new BusinessException(BusinessErrorMessage.CUSTOMER_ALREADY_EXIST)
                ))
                .switchIfEmpty(Mono.empty())
                .then();

    }
}
