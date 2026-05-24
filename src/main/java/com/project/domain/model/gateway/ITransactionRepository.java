package com.project.domain.model.gateway;
import com.project.domain.model.entity.TransactionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionRepository {

    Mono<TransactionEntity> create(TransactionEntity trx);
    Flux<TransactionEntity> getAllTransactions();
    Flux<TransactionEntity> getTransactionByCb(int code_cb);
}
