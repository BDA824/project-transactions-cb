package com.project.domain.model;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICorrespondentRepository {

    Mono<correspondentEntity> create(correspondentEntity crp);
    Mono<List<correspondentEntity>> getAllCorrespondents();
    Mono<correspondentEntity> getCorrespondentById(correspondentEntity crp);
    Mono<correspondentEntity> updateCorrespondent(correspondentEntity crp);
    Mono<Void> updateLastCloser(double last_closer);
}
