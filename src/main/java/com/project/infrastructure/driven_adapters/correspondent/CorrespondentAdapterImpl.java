package com.project.infrastructure.driven_adapters.correspondent;

import com.project.domain.exception.exception_classes.TechnicalExceptions;
import com.project.domain.exception.message.TechnicalErrorMessage;
import com.project.domain.model.entity.CorrespondentEntity;
import com.project.domain.model.gateway.ICorrespondentRepository;
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
    public Mono<CorrespondentEntity> create(CorrespondentEntity correspondent)
    {

        return correspondentAdapterRepository
                .save(correspondentMapper.toData(correspondent))
                .map(correspondentMapper::toEntity)
                .onErrorMap(ex -> new TechnicalExceptions(ex, TechnicalErrorMessage.CORRESPONDENT_SAVE));
    }

    @Override
    public Flux<CorrespondentEntity> getAllCorrespondents() {

        return correspondentAdapterRepository
                .findAll()
                .map(correspondentMapper::toEntity);
    }

    @Override
    public Mono<CorrespondentEntity> getCorrespondentById(int codeCB) {

        return correspondentAdapterRepository
                .findCorrespondentByCodeCb(codeCB)
                .map(correspondentMapper::toEntity)
                .onErrorMap(ex -> new TechnicalExceptions(ex, TechnicalErrorMessage.CORRESPONDENT_FIND));
    }

    @Override
    public Flux<CorrespondentEntity> findCorrespondentById(int id) {

        return correspondentAdapterRepository
                .findCorrespondentByIdCustomer(id)
                .map(correspondentMapper::toEntity)
                .onErrorMap(ex -> new TechnicalExceptions(ex, TechnicalErrorMessage.CORRESPONDENT_FIND));
    }

    @Override
    public Mono<CorrespondentEntity> updateCorrespondent(CorrespondentEntity crp) {

        return correspondentAdapterRepository
                .updateCorrespondentInfo(
                        crp.getLocation(),
                        crp.getLastClosed(),
                        crp.getCodeCB()
                )
                .flatMap(rw ->
                    correspondentAdapterRepository.findCorrespondentByCodeCb(crp.getCodeCB())
                )
                .map(correspondentMapper::toEntity)
                .onErrorMap(ex -> new TechnicalExceptions(ex, TechnicalErrorMessage.CORRESPONDENT_FIND));
    }
}
