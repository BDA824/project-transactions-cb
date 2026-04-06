package com.project.domain.model.usecase.correspondent.update;

import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.entity.correspondentEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateCorrespondentUseCase {

    public final ICorrespondentRepository correspondentRepository;

    public Mono<correspondentEntity> updateCorrespondent(correspondentEntity cb) {

        return correspondentRepository.findCorrespondentById(cb.getCode_cb())
                .switchIfEmpty(Mono.error(
                        new Exception("Correspondent not found")
                ))
                .flatMap(cbExist -> {
                    cbExist.setApproved_amount(cb.getApproved_amount());
                    cbExist.setLocation(cb.getLocation());
                    cbExist.setLast_clousure(cb.getLast_clousure());

                    return correspondentRepository.updateCorrespondent(cbExist);
                });
    }

}
