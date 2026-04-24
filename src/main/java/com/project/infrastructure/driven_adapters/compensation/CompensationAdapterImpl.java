package com.project.infrastructure.driven_adapters.compensation;

import com.project.domain.model.entity.compensationEntity;
import com.project.domain.model.gateway.ICompensationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CompensationAdapterImpl implements ICompensationRepository {

    private final CompensationAdapterRepository compensationAdapterRepository;
    private final CompensationMapper compensationMapper;

    @Override
    public Mono<Void> create(compensationEntity cmp)
    {

        return compensationAdapterRepository
                .save(compensationMapper.toData(cmp))
                .then();
    }

    @Override
    public Flux<compensationEntity> getAllCompensations() {
        return Flux.empty();
    }

    @Override
    public Mono<compensationEntity> getCompensationByCb(int code_cb)
    {
        return Mono.empty();
    }

    @Override
    public Mono<compensationEntity> updateCompensation(compensationEntity cmp) {
        return Mono.empty();
    }
}
