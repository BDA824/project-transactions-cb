package com.project.domain.model.gateway;
import com.project.domain.model.entity.CompensationEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICompensationRepository {

    Mono<CompensationEntity> create(CompensationEntity cmp);
    Flux<CompensationEntity> getAllCompensations();
    Mono<CompensationEntity> getCompensationByCb(int code_cb);
    Mono<CompensationEntity> updateCompensation(CompensationEntity cmp);
    Mono<CompensationEntity> updateClosed(double last_closed, int code_cb);
}
