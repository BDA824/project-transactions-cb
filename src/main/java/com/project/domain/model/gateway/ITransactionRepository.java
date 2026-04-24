package com.project.domain.model.gateway;
import com.project.domain.model.entity.transactionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITransactionRepository {

    Mono<transactionEntity> create(transactionEntity trx);
    Flux<transactionEntity> getAllTransactions();
    Flux<transactionEntity> getTransactionByCb(int code_cb);
}
