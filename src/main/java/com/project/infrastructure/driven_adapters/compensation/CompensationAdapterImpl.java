package com.project.infrastructure.driven_adapters.compensation;

import com.project.domain.model.entity.CompensationEntity;
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
    public Mono<CompensationEntity> create(CompensationEntity cmp)
    {

        return compensationAdapterRepository
                .save(compensationMapper.toData(cmp))
                .map(compensationMapper::toEntity);
    }

    @Override
    public Flux<CompensationEntity> getAllCompensations() {
        return Flux.empty();
    }

    @Override
    public Mono<CompensationEntity> getCompensationByCb(int code_cb)
    {

        return compensationAdapterRepository
                .getCompensationByCb(code_cb)
                .map(compensationMapper::toEntity);

    }

    @Override
    public Mono<CompensationEntity> updateCompensation(CompensationEntity cmp) {

        return compensationAdapterRepository
                .updateCompensationByTransaction(
                        cmp.getCodeCB(),
                        cmp.getTotalValue(),
                        cmp.getRemainingValue(),
                        cmp.getState().name()
                )
                .flatMap(rw -> compensationAdapterRepository
                        .getCompensationByCb(cmp.getCodeCB()))
                .map(compensationMapper::toEntity);

    }

    @Override
    public Mono<CompensationEntity> updateClosed(
            double last_closed,
            int code_cb
            ) {

        return compensationAdapterRepository
                .updateCompensation(code_cb, last_closed)
                .flatMap(rw -> compensationAdapterRepository.getCompensationByCb(code_cb))
                .map(compensationMapper::toEntity);
    }
}
