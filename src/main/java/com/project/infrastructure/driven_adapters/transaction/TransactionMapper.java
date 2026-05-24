package com.project.infrastructure.driven_adapters.transaction;

import com.project.domain.model.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionAdapterData toData(TransactionEntity entity);

    TransactionEntity toEntity(TransactionAdapterData data);
}
