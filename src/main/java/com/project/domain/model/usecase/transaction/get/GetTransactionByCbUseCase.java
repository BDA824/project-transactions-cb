package com.project.domain.model.usecase.transaction.get;

import com.project.domain.model.entity.TransactionEntity;
import com.project.domain.model.gateway.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetTransactionByCbUseCase {

    private final ITransactionRepository transactionRepository;

    public Flux<TransactionEntity> getTransactionsByCb(int code_cb) {

        return transactionRepository.getTransactionByCb(code_cb);
    }

}
