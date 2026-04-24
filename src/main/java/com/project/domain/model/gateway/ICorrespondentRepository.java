package com.project.domain.model.gateway;
import com.project.domain.model.entity.correspondentEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICorrespondentRepository {

    Mono<correspondentEntity> create(correspondentEntity crp);
    Flux<correspondentEntity> getAllCorrespondents();
    Mono<correspondentEntity> getCorrespondentById(int codeCB);
    Mono<correspondentEntity> findCorrespondentById(int id);
    Mono<correspondentEntity> updateCorrespondent(correspondentEntity crp);
}
