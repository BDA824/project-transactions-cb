package com.project.domain.model.usecase.transaction.get;

import com.project.domain.model.gateway.ITransactionRepository;
import com.project.domain.model.entity.transactionEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetTransactionByCbUseCase {

    private final ITransactionRepository transactionRepository;

    public Flux<transactionEntity> getTransactionsByCb(int code_cb) {

        return transactionRepository.getTransactionByCb(code_cb);
    }

}
