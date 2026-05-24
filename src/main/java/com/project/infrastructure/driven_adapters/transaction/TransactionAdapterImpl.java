package com.project.infrastructure.driven_adapters.transaction;


import com.project.domain.model.entity.TransactionEntity;
import com.project.domain.model.gateway.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class TransactionAdapterImpl implements ITransactionRepository {

    private final TransactionAdapterRepository transactionAdapterRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Flux<TransactionEntity> getTransactionByCb(int code_cb) {

        return transactionAdapterRepository
                .getTransactionsByCb(code_cb)
                .map(transactionMapper::toEntity);

    }

    @Override
    public Flux<TransactionEntity> getAllTransactions() {

        return transactionAdapterRepository
                .findAll()
                .map(transactionMapper::toEntity);

    }

    @Override
    public Mono<TransactionEntity> create(TransactionEntity trx) {

        return transactionAdapterRepository
                .save(transactionMapper.toData(trx))
                .map(transactionMapper::toEntity);

    }
}
