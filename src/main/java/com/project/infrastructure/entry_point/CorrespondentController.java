package com.project.infrastructure.entry_point;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.exception.message.BusinessErrorMessage;
import com.project.domain.model.entity.CorrespondentEntity;
import com.project.domain.model.usecase.correspondent.create.CreateCorrespndentUseCase;
import com.project.domain.model.usecase.correspondent.get.GetAllCorrespondentUseCase;
import com.project.domain.model.usecase.correspondent.get.GetCorrespondentByCodeCbUseCase;
import com.project.domain.model.usecase.correspondent.get.GetCorrespondentByIdUseCase;
import com.project.domain.model.usecase.correspondent.update.UpdateCorrespondentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/correspondents")
@RequiredArgsConstructor
public class CorrespondentController {

    private final CreateCorrespndentUseCase createUC;
    private final GetAllCorrespondentUseCase getAllUC;
    private final GetCorrespondentByCodeCbUseCase getByCodeCbUC;
    private final GetCorrespondentByIdUseCase getByIdUC;
    private final UpdateCorrespondentUseCase updateUC;

    @PostMapping(path = "/create")
    public Mono<ResponseEntity<Object>> createCB(
            @RequestBody CorrespondentEntity cb) {

        return createUC
                .createCB(cb)
                .map(r -> ResponseEntity.ok().body((Object) r))
                .onErrorResume(BusinessException.class, err -> Mono.just(
                        ResponseEntity.badRequest().body(err.getBusinessErrorMessage())
                ));

    }

    @GetMapping(path = "/")
    public Flux<CorrespondentEntity> getAllCb() {

        return getAllUC
                .getAllCorrespondents();
    }

    @GetMapping(path = "/{code_cb}")
    public Mono<ResponseEntity<Object>> getByCodeCb(
            @PathVariable int code_cb
    ) {

        return getByCodeCbUC
                .getCorrespondentById(code_cb)
                .map(r -> ResponseEntity
                        .ok()
                        .body((Object) r)
                )
                .onErrorResume(BusinessException.class, err -> Mono.just(
                        ResponseEntity.status(404).body(err.getBusinessErrorMessage())
                ));
    }

    @GetMapping(path = "/by-customer/{identification}")
    public Mono<ResponseEntity<Object>> getById(
            @PathVariable int identification
    ) {

        return getByIdUC
                .getCorrespondentByIdCustomer(identification)
                .collectList()
                .flatMap(list -> {
                    if (list.isEmpty()) {
                        return Mono.error(
                                new BusinessException(
                                        BusinessErrorMessage.CUSTOMER_NOT_FOUND
                                )
                        );
                    }
                    return Mono.just(
                            ResponseEntity.ok().<Object>body(list)
                    );
                })
                .onErrorResume(BusinessException.class, ex ->
                        Mono.just(
                                ResponseEntity.status(404).body(ex.getBusinessErrorMessage())
                        ));
    }

    @PostMapping(path = "/update")
    public Mono<ResponseEntity<Object>> updateCB(
            @RequestBody CorrespondentEntity cb) {

        return updateUC
                .updateCorrespondent(cb)
                .map(response -> ResponseEntity
                        .ok()
                        .body((Object) response)
                )
                .onErrorResume(BusinessException.class, err -> Mono.just(
                        ResponseEntity.status(404).body(err.getBusinessErrorMessage())
                ));

    }
}
