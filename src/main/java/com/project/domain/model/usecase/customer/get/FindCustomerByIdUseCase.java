package com.project.domain.model.usecase.customer.get;

import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.entity.customerEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindCustomerByIdUseCase {

    private final ICustomerRepository customerRepository;

    public Mono<customerEntity> getCustomerById(int id)
    {
        return customerRepository.findCustomerById(id);
    }
}
