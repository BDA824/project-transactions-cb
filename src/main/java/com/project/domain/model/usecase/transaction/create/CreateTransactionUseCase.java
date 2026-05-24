package com.project.domain.model.usecase.transaction.create;

import com.project.domain.model.entity.CompensationEntity;
import com.project.domain.model.entity.TransactionEntity;
import com.project.domain.model.enums.CompensationStatus;
import com.project.domain.model.enums.TransactionStatus;
import com.project.domain.model.gateway.ICompensationRepository;
import com.project.domain.model.gateway.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateTransactionUseCase {

    private final ITransactionRepository transactionRepository;
    private final ICompensationRepository compensationRepository;

    public Mono<TransactionEntity> createTransaction(TransactionEntity trx) {

        return compensationRepository.getCompensationByCb(trx.getCodeCB())
                .switchIfEmpty(Mono.error(
                        new Exception("Compensation not found or in state pending " + trx.getCodeCB())
                ))
                .flatMap(compensation -> {
                    trx.setState(TransactionStatus.APPROVED);
                    return transactionRepository.create(trx);
                })
                .flatMap(trxC -> updateCompensation(trx)
                        .thenReturn(trxC));
    }

    private Mono<CompensationEntity> updateCompensation(
            TransactionEntity trx
    ) {

        return compensationRepository
                .getCompensationByCb(trx.getCodeCB())
                .flatMap(cmp -> {
                    if (cmp.getTotalValue() == 0)
                    {
                        return Mono.error(new Exception("Compensation cannot be completed"));
                    }
                    else {
                        double value = cmp.getRemainingValue() - trx.getAmountTrx();
                        if(value > 1)
                        {
                            cmp.setState(CompensationStatus.CREDIT);
                            cmp.setRemainingValue(value);
                        }
                        else {
                            cmp.setState(CompensationStatus.COMPENSED);
                            cmp.setRemainingValue(value);
                        }
                    }

                    return compensationRepository.updateCompensation(cmp);
                });
    }

}
