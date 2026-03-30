package com.project.domain.model;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICustomerRepository {

    Mono<customerEntity> create(customerEntity cl);
    Mono<customerEntity> findCustomerById(int id);
    Mono<List<customerEntity>> getAllCustomers();
    Mono<customerEntity> updateCustomer(customerEntity cl);
}
