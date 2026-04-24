package com.project.infrastructure.entry_point;

import com.project.domain.model.entity.correspondentEntity;
import com.project.domain.model.usecase.correspondent.create.CreateCorrespndentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/correspondents")
@RequiredArgsConstructor
public class CorrespondentController {

    private final CreateCorrespndentUseCase createUC;

    @PostMapping(path = "/create")
    public Mono<ResponseEntity<correspondentEntity>> createCB(
            @RequestBody correspondentEntity cb) {

        return createUC
                .createCB(cb)
                .map(response -> ResponseEntity
                        .ok()
                        .body(response)
                );

    }

}
