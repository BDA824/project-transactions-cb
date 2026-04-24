package com.project.infrastructure.driven_adapters.correspondent;

import com.project.domain.model.entity.correspondentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CorrespondentMapper {

    correspondentEntity toEntity(CorrespondentAdapterData data);

    CorrespondentAdapterData toData(correspondentEntity entity);
}
