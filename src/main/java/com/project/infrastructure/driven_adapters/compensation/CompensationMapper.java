package com.project.infrastructure.driven_adapters.compensation;

import com.project.domain.model.entity.CompensationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompensationMapper {

    @Mapping(target = "state", expression = "java(com.project.domain.model.enums.CompensationStatus.valueOf(data.getState()))")
    CompensationEntity toEntity(CompensationAdapterData data);

    @Mapping(target = "state", expression = "java(entity.getState().name())")
    CompensationAdapterData toData(CompensationEntity entity);

}
