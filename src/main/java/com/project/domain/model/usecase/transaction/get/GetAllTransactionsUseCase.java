package com.project.domain.model.usecase.transaction.get;

import com.project.domain.model.gateway.ITransactionRepository;
import com.project.domain.model.entity.transactionEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class GetAllTransactionsUseCase {

    private final ITransactionRepository transactionRepository;

    public Flux<transactionEntity> getAllTransactions(){
        return transactionRepository.getAllTransactions();
    }
}
