package com.project.infrastructure.driven_adapters.compensation;

import com.project.domain.model.enums.CompensationStatus;
import com.project.infrastructure.driven_adapters.correspondent.CorrespondentAdapterData;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CompensationAdapterRepository extends ReactiveCrudRepository<CompensationAdapterData, Integer> {

    @Query("SELECT * " +
            "FROM compensation " +
            "WHERE code_cb = :code_cb")
    Mono<CompensationAdapterData> getCompensationByCb (
            @Param("code_cb") int code_cb
    );

    @Modifying
    @Query("UPDATE compensation SET " +
            "total_value = :total_value, " +
            "remaining_value = :total_value " +
            "WHERE code_cb = :code_cb"
            )
    Mono<Integer> updateCompensation(
            @Param("code_cb") int code_cb,
            @Param("total_value") double total_value
    );

    @Modifying
    @Query("UPDATE compensation SET " +
            "total_value = :total_value, " +
            "remaining_value = :remaining_value, " +
            "state = :state " +
            "WHERE code_cb = :code_cb"
    )
    Mono<Integer> updateCompensationByTransaction(
            @Param("code_cb") int code_cb,
            @Param("total_value") double total_value,
            @Param("remaining_value") double remaining_value,
            @Param("state") String state
            );
}
