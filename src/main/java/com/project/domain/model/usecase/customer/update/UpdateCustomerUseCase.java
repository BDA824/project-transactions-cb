package com.project.domain.model.usecase.customer.update;

import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.entity.customerEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public Mono<customerEntity> updateCustomer(customerEntity cl) {
        return customerRepository.findCustomerById(cl.getIdentification())
                .switchIfEmpty(Mono.error(
                        new Exception("User not found")
                ))
                .flatMap(customerExist -> {
                    customerExist.setAddress(cl.getAddress());
                    customerExist.setPhone(cl.getPhone());
                    return customerRepository.updateCustomer(customerExist);
                });
    }
}
