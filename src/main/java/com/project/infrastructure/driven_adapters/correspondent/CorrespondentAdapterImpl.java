package com.project.infrastructure.driven_adapters.correspondent;

import com.project.domain.exception.exception_classes.TechnicalExceptions;
import com.project.domain.exception.message.TechnicalErrorMessage;
import com.project.domain.model.entity.correspondentEntity;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.infrastructure.driven_adapters.customer.CustomerAdapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CorrespondentAdapterImpl implements ICorrespondentRepository {

    private final CorrespondentAdapterRepository correspondentAdapterRepository;
    private final CorrespondentMapper correspondentMapper;

    @Override
    public Mono<correspondentEntity> create(correspondentEntity correspondent)
    {

        return correspondentAdapterRepository
                .save(correspondentMapper.toData(correspondent))
                .map(correspondentMapper::toEntity)
                .onErrorMap(ex -> new TechnicalExceptions(ex, TechnicalErrorMessage.CORRESPONDENT_SAVE));
    }

    @Override
    public Flux<correspondentEntity> getAllCorrespondents() {

        return correspondentAdapterRepository
                .findAll()
                .map(correspondentMapper::toEntity);
    }

    @Override
    public Mono<correspondentEntity> getCorrespondentById(int codeCB) {

        return correspondentAdapterRepository
                .findCorrespondentByCodeCb(codeCB)
                .map(correspondentMapper::toEntity)
                .onErrorMap(ex -> new TechnicalExceptions(ex, TechnicalErrorMessage.CORRESPONDENT_FIND));
    }

    @Override
    public Flux<correspondentEntity> findCorrespondentById(int id) {

        return correspondentAdapterRepository
                .findCorrespondentByIdCustomer(id)
                .map(correspondentMapper::toEntity)
                .onErrorMap(ex -> new TechnicalExceptions(ex, TechnicalErrorMessage.CORRESPONDENT_FIND));
    }

    @Override
    public Mono<correspondentEntity> updateCorrespondent(correspondentEntity crp) {

        return correspondentAdapterRepository
                .updateCorrespondentInfo(
                        crp.getLocation(),
                        crp.getLast_clousure(),
                        crp.getCode_cb()
                )
                .flatMap(rw ->
                    correspondentAdapterRepository.findCorrespondentByCodeCb(crp.getCode_cb())
                )
                .map(correspondentMapper::toEntity)
                .onErrorMap(ex -> new TechnicalExceptions(ex, TechnicalErrorMessage.CORRESPONDENT_FIND));
    }
}
