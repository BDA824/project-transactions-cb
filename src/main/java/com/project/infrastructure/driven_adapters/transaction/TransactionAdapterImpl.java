package com.project.infrastructure.driven_adapters.transaction;


import com.project.domain.model.entity.transactionEntity;
import com.project.domain.model.gateway.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class TransactionAdapterImpl implements ITransactionRepository {

    private final TransactionAdapterRepository transactionAdapterRepository;

    @Override
    public Flux<transactionEntity> getTransactionByCb(int code_cb) {
        return Flux.empty();
    }

    @Override
    public Flux<transactionEntity> getAllTransactions() {
        return Flux.empty();
    }

    @Override
    public Mono<transactionEntity> create(transactionEntity trx) {
        return Mono.empty();
    }
}
