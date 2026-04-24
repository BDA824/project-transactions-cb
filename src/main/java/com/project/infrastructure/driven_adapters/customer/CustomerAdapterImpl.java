package com.project.infrastructure.driven_adapters.customer;

import com.project.domain.model.entity.customerEntity;
import com.project.domain.model.gateway.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CustomerAdapterImpl implements ICustomerRepository {

    private final CustomerAdapterRepository customerAdapterRepository;
    private final CustomerAdapterMapper customerAdapterMapper;

    @Override
    @Transactional
    public Mono<customerEntity> create(customerEntity cl)
    {
        return customerAdapterRepository
                .save(customerAdapterMapper.toData(cl))
                .map(customerAdapterMapper::toEntity);
    }

    @Override
    public Mono<customerEntity> findCustomerById(int id)
    {
        return customerAdapterRepository
                .findById(id)
                .map(customerAdapterMapper::toEntity);
    }

    @Override
    public Flux<customerEntity> getAllCustomers()
    {
        return customerAdapterRepository
                .findAll()
                .map(customerAdapterMapper::toEntity);
    }

    @Override
    public Mono<customerEntity> updateCustomer(customerEntity cl)
    {
        return customerAdapterRepository
                .updateCustomer(
                        cl.getIdentification(),
                        cl.getAge(),
                        cl.getPhone(),
                        cl.getAddress()
                )
                .flatMap(rowsAffected ->
                {
                    if(rowsAffected == 0)
                    {
                        return Mono.error(new RuntimeException(
                                "Customer not found: " + cl.getIdentification()
                        ));
                    }
                    return customerAdapterRepository.findById(cl.getIdentification());
                })
                .map(customerAdapterMapper::toEntity);
    }

}
