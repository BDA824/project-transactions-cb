package com.project.infrastructure.driven_adapters.transaction;

import com.project.domain.model.entity.transactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionAdapterData toData(transactionEntity entity);

    transactionEntity toEntity(TransactionAdapterData data);
}
