package com.project.infrastructure.entry_point;

import com.project.domain.model.entity.transactionEntity;
import com.project.domain.model.usecase.transaction.create.CreateTransactionUseCase;
import com.project.domain.model.usecase.transaction.get.GetAllTransactionsUseCase;
import com.project.domain.model.usecase.transaction.get.GetTransactionByCbUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransactionController {

    private final GetAllTransactionsUseCase getAllUC;
    private final CreateTransactionUseCase createTrxUC;
    private final GetTransactionByCbUseCase getByCbUC;

    @GetMapping(path = "/")
    public Flux<transactionEntity> getAllTransactions() {

        return getAllUC
                .getAllTransactions();
    }

    @PostMapping(path = "/create")
    public Mono<ResponseEntity<transactionEntity>> createTransaction(@RequestBody transactionEntity trx){

        return createTrxUC
                .createTransaction(trx)
                .map(r -> ResponseEntity
                        .ok()
                        .body(r));

    }

    @GetMapping(path = "/{code_cb}")
    public Flux<transactionEntity> getTransactionsByCb(
            @PathVariable int code_cb
    ) {

        return getByCbUC
                .getTransactionsByCb(code_cb);

    }

}
