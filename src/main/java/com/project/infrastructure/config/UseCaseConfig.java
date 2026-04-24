package com.project.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.project.domain.model.usecase",  // Paquete con los casos de uso
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "^.+UseCase$"                     // Convencion de nombres, casos de uso deben terminar con esta convencion en el nombre ej: CreateCustomerUseCase
                )
        },
        useDefaultFilters = false
)
public class UseCaseConfig {
}
