package com.project.domain.model.usecase.correspondent.create;

import com.project.domain.model.gateway.ICompensationRepository;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.entity.compensationEntity;
import com.project.domain.model.entity.correspondentEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.Random;

@RequiredArgsConstructor
public class CreateCorrespndentUseCase {

    private final ICorrespondentRepository correspondentRepository;
    private final ICompensationRepository cmpRepository;

    Random random = new Random();

    public Mono<correspondentEntity> createCB(correspondentEntity cb) {
        compensationEntity cmp = new compensationEntity();
        cmp = new compensationEntity(random.nextInt(1001,9999),
                cb.getCode_cb(),cmp.setDate_cmp(),0.0,0.0,cmp.getState());
        cmpRepository.create(cmp);
        return correspondentRepository.create(cb);
    }


}
