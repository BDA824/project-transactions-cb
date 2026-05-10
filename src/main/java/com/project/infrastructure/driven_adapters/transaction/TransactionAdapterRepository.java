package com.project.infrastructure.driven_adapters.transaction;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TransactionAdapterRepository extends ReactiveCrudRepository<TransactionAdapterData, Integer> {

    @Query("SELECT * FROM transaction WHERE code_cb = :code_cb ")
    Flux<TransactionAdapterData> getTransactionsByCb(
            @Param("code_cb") int code_cb
    );
}
