package com.project.infrastructure.driven_adapters.customer;

import com.project.domain.exception.exception_classes.TechnicalExceptions;
import com.project.domain.exception.message.TechnicalErrorMessage;
import com.project.domain.model.entity.CustomerEntity;
import com.project.domain.model.gateway.ICustomerRepository;
import io.r2dbc.spi.R2dbcException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
    public Mono<CustomerEntity> create(CustomerEntity cl)
    {
        return customerAdapterRepository
                .save(customerAdapterMapper.toData(cl))
                .map(customerAdapterMapper::toEntity)
                .onErrorMap(ex -> new TechnicalExceptions(ex, TechnicalErrorMessage.CUSTOMER_SAVE));
    }

    @Override
    public Mono<CustomerEntity> findCustomerById(int id)
    {
        return customerAdapterRepository
                .findById(id)
                .map(customerAdapterMapper::toEntity);
    }

    @Override
    public Flux<CustomerEntity> getAllCustomers()
    {
        return customerAdapterRepository
                .findAll()
                .map(customerAdapterMapper::toEntity);
    }

    @Override
    public Mono<CustomerEntity> updateCustomer(CustomerEntity cl)
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
                        return Mono.error(new TechnicalExceptions(TechnicalErrorMessage.CUSTOMER_FIND));
                    }
                    return customerAdapterRepository.findById(cl.getIdentification());
                })
                .map(customerAdapterMapper::toEntity);
    }

}
