package com.project.infrastructure.driven_adapters.correspondent;

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
                .map(correspondentMapper::toEntity);
    }

    @Override
    public Flux<correspondentEntity> getAllCorrespondents() {
        return Flux.empty();
    }

    @Override
    public Mono<correspondentEntity> getCorrespondentById(int codeCB) {
        return Mono.empty();
    }

    @Override
    public Mono<correspondentEntity> findCorrespondentById(int id) {
        return Mono.empty();
    }

    @Override
    public Mono<correspondentEntity> updateCorrespondent(correspondentEntity crp) {
        return Mono.empty();
    }
}
