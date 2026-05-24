package com.project.infrastructure.driven_adapters.correspondent;

import com.project.domain.model.entity.CorrespondentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CorrespondentMapper {

    CorrespondentEntity toEntity(CorrespondentAdapterData data);

    CorrespondentAdapterData toData(CorrespondentEntity entity);
}
