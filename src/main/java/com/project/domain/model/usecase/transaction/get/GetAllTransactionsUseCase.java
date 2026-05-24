package com.project.domain.model.usecase.transaction.get;

import com.project.domain.model.gateway.ITransactionRepository;
import com.project.domain.model.entity.TransactionEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllTransactionsUseCase {

    private final ITransactionRepository transactionRepository;

    public Flux<TransactionEntity> getAllTransactions(){
        return transactionRepository.getAllTransactions();
    }
}
