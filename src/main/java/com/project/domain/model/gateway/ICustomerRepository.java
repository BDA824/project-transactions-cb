package com.project.domain.model.gateway;
import com.project.domain.model.entity.customerEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICustomerRepository {

    Mono<customerEntity> create(customerEntity cl);
    Mono<customerEntity> findCustomerById(int id);
    Flux<customerEntity> getAllCustomers();
    Mono<customerEntity> updateCustomer(customerEntity cl);
}
