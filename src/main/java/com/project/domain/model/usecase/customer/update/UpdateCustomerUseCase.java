package com.project.domain.model.usecase.customer.update;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.exception.message.BusinessErrorMessage;
import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public Mono<CustomerEntity> updateCustomer(CustomerEntity cl) {
        return customerRepository.findCustomerById(cl.getIdentification())
                .switchIfEmpty(Mono.error(
                        new BusinessException(BusinessErrorMessage.CUSTOMER_NOT_FOUND)
                ))
                .flatMap(customerExist -> {
                    customerExist.setAddress(cl.getAddress());
                    customerExist.setPhone(cl.getPhone());
                    return customerRepository.updateCustomer(customerExist);
                });
    }
}
