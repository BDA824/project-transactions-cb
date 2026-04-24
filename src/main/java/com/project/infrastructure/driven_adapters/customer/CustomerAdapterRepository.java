package com.project.infrastructure.driven_adapters.customer;

import com.project.domain.model.entity.customerEntity;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CustomerAdapterRepository extends ReactiveCrudRepository<CustomerAdapterData, Integer> {

    @Modifying
    @Query("UPDATE customer SET " +
            "age = :age, " +
            "phone = :phone, " +
            "address = :address " +
            "WHERE id = :identification")
    Mono<Integer> updateCustomer(
            @Param("identification") Integer identification,
            @Param("age")           Integer age,
            @Param("phone")         Long phone,
            @Param("address")       String address
    );
}
