package com.project.domain.model.gateway;
import com.project.domain.model.entity.compensationEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICompensationRepository {

    Mono<Void> create(compensationEntity cmp);
    Flux<compensationEntity> getAllCompensations();
    Mono<compensationEntity> getCompensationByCb(int code_cb);
    Mono<compensationEntity> updateCompensation(compensationEntity cmp);
}
