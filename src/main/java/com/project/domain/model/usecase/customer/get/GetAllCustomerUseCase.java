package com.project.domain.model.usecase.customer.get;

import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.entity.customerEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class GetAllCustomerUseCase {

    private final ICustomerRepository customerReporitory;

    public Mono<List<customerEntity>> getAllCustomers() {
        return customerReporitory.getAllCustomers();
    }
}
