package com.project.domain.model.gateway;
import com.project.domain.model.entity.CustomerEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerRepository {

    Mono<CustomerEntity> create(CustomerEntity cl);
    Mono<CustomerEntity> findCustomerById(int id);
    Flux<CustomerEntity> getAllCustomers();
    Mono<CustomerEntity> updateCustomer(CustomerEntity cl);
}
