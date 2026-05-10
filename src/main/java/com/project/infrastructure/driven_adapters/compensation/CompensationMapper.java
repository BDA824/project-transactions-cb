package com.project.infrastructure.driven_adapters.compensation;

import com.project.domain.model.entity.compensationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompensationMapper {

    @Mapping(target = "state", expression = "java(com.project.domain.model.enums.CompensationStatus.valueOf(data.getState()))")
    compensationEntity toEntity(CompensationAdapterData data);

    @Mapping(target = "state", expression = "java(entity.getState().name())")
    CompensationAdapterData toData(compensationEntity entity);

}
