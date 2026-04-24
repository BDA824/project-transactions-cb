package com.project.infrastructure.driven_adapters.compensation;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CompensationAdapterRepository extends ReactiveCrudRepository<CompensationAdapterData, Integer> {
}
