package com.project.domain.model.gateway;
import com.project.domain.model.entity.CorrespondentEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICorrespondentRepository {

    Mono<CorrespondentEntity> create(CorrespondentEntity crp);
    Flux<CorrespondentEntity> getAllCorrespondents();
    Mono<CorrespondentEntity> getCorrespondentById(int codeCB);
    Flux<CorrespondentEntity> findCorrespondentById(int id);
    Mono<CorrespondentEntity> updateCorrespondent(CorrespondentEntity crp);
}
