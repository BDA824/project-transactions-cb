package com.project.infrastructure.driven_adapters.compensation;

import com.project.domain.model.entity.compensationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompensationMapper {

    compensationEntity toEntity(CompensationAdapterData data);

    CompensationAdapterData toData(compensationEntity entity);

}
