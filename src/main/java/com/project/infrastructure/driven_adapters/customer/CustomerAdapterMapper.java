package com.project.infrastructure.driven_adapters.customer;

import com.project.domain.model.entity.customerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerAdapterMapper {

    CustomerAdapterData toData(customerEntity entity);

    customerEntity toEntity(CustomerAdapterData data);

}
