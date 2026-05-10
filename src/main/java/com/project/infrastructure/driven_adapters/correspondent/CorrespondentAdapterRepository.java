package com.project.infrastructure.driven_adapters.correspondent;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CorrespondentAdapterRepository extends ReactiveCrudRepository<CorrespondentAdapterData, Integer> {

    @Query("SELECT * " +
            "FROM correspondent " +
            "WHERE code_cb = :code_cb")
    Mono<CorrespondentAdapterData> findCorrespondentByCodeCb(@Param("code_cb") int code_cb);

    @Query("SELECT * " +
            "FROM correspondent " +
            "WHERE id_customer = :identification")
    Flux<CorrespondentAdapterData> findCorrespondentByIdCustomer(@Param("identification")int identification);

    @Modifying
    @Query("UPDATE correspondent " +
            "SET location = :location, " +
            "last_clousure = :last_clousure " +
            "WHERE code_cb = :code_cb")
    Mono<Integer> updateCorrespondentInfo(
            @Param("location") String location,
            @Param("last_clousure")double last_clousure,
            @Param("code_cb")int code_cb);

    /*
    - Before create a correspondent validate that customer already exist and
    correspondent with code_cb doesn't exist
    - In getCorrespondentById and update verify that correspondent if already exist
    do the same for the findCorrespondentById method
    */
}
