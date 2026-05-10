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
    public Mono<compensationEntity> create(compensationEntity cmp)
    {

        return compensationAdapterRepository
                .save(compensationMapper.toData(cmp))
                .map(compensationMapper::toEntity);
    }

    @Override
    public Flux<compensationEntity> getAllCompensations() {
        return Flux.empty();
    }

    @Override
    public Mono<compensationEntity> getCompensationByCb(int code_cb)
    {

        return compensationAdapterRepository
                .getCompensationByCb(code_cb)
                .map(compensationMapper::toEntity);

    }

    @Override
    public Mono<compensationEntity> updateCompensation(compensationEntity cmp) {

        return compensationAdapterRepository
                .updateCompensationByTransaction(
                        cmp.getCode_cb(),
                        cmp.getTotal_value(),
                        cmp.getRemaining_value(),
                        cmp.getState().name()
                )
                .flatMap(rw -> compensationAdapterRepository
                        .getCompensationByCb(cmp.getCode_cb()))
                .map(compensationMapper::toEntity);

    }

    @Override
    public Mono<compensationEntity> updateClosed(
            double last_closed,
            int code_cb
            ) {

        return compensationAdapterRepository
                .updateCompensation(code_cb, last_closed)
                .flatMap(rw -> compensationAdapterRepository.getCompensationByCb(code_cb))
                .map(compensationMapper::toEntity);
    }
}
