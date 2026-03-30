package com.project.domain.model;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICompensationReporitory {

    Mono<compensationEntity> create(compensationEntity cmp);
    Mono<List<compensationEntity>> getAllCompensations();
    Mono<compensationEntity> getCompensationById(compensationEntity cmp);
    Mono<compensationEntity> getCompensationByCb(compensationEntity cmp);
    Mono<compensationEntity> updateCompensation(compensationEntity cmp);
}
