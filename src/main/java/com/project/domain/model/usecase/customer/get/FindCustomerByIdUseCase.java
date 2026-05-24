package com.project.domain.model.usecase.customer.get;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.exception.message.BusinessErrorMessage;
import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindCustomerByIdUseCase {

    private final ICustomerRepository customerRepository;

    public Mono<CustomerEntity> getCustomerById(int id)
    {

        return customerRepository
                .findCustomerById(id)
                .switchIfEmpty(Mono.error(
                        new BusinessException(BusinessErrorMessage.CUSTOMER_NOT_FOUND)
                ));

    }
}
