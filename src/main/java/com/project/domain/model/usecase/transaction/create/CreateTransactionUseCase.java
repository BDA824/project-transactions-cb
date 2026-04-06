package com.project.domain.model.usecase.transaction.create;

import com.project.domain.model.entity.compensationEntity;
import com.project.domain.model.entity.transactionEntity;
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

    public Mono<transactionEntity> createTransaction(transactionEntity trx) {

        return compensationRepository.getCompensationByCb(trx.getCode_cb())
                .switchIfEmpty(Mono.error(
                        new Exception("Compensation not found or in state pending")
                ))
                .flatMap(compensation -> {
                    updateCompensation(compensation, trx);
                    trx.setState(TransactionStatus.APPROVED);
                    return transactionRepository.create(trx);
                });
    }

    private Mono<compensationEntity> updateCompensation(
            compensationEntity cmp, transactionEntity trx
    ) {

        double value = 0;
        if (cmp.getTotal_value() == 0)
        {
            return Mono.error(new Exception("Compensation cannot be completed"));
        }
        else {
            value = cmp.getTotal_value() - trx.getAmount_trx();
            if(value > 1)
            {
                cmp.setState(CompensationStatus.CREDIT);
                cmp.setRemaining_value(value);
            }
            cmp.setState(CompensationStatus.COMPENSED);
            cmp.setRemaining_value(value);
        }

        return compensationRepository.updateCompensation(cmp);
    }

}
