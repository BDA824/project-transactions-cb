package com.project.domain.model.usecase.customer.create;

import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.entity.customerEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public Mono<customerEntity> saveCustomer(customerEntity cl)
    {
        cl.setDate_vinculation(LocalDate.now());
        return customerRepository.create(cl);
    }
}
