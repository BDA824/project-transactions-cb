package com.project.infrastructure.driven_adapters.transaction;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransactionAdapterRepository extends ReactiveCrudRepository<TransactionAdapterData, Integer> {
}
