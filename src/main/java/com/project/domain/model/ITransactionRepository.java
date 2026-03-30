package com.project.domain.model;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITransactionRepository {

    Mono<transactionEntity> create(transactionEntity trx);
    Mono<List<transactionEntity>> getAllTransactions();
    Mono<transactionEntity> getTransactionById(transactionEntity trx);
    Mono<transactionEntity> getTransactionByCb(transactionEntity trx);
    Mono<transactionEntity> updateTransaction(transactionEntity trx);
}
