package com.project.infrastructure.driven_adapters.customer;

import com.project.domain.model.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAdapterMapper {

    CustomerAdapterData toData(CustomerEntity entity);

    CustomerEntity toEntity(CustomerAdapterData data);

}
