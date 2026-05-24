package com.project.domain.model.usecase.customer.get;

import com.project.domain.model.entity.CustomerEntity;
import com.project.domain.model.gateway.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public Flux<CustomerEntity> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}
