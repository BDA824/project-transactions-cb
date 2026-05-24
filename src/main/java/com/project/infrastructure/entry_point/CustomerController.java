package com.project.infrastructure.entry_point;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.model.entity.CustomerEntity;
import com.project.domain.model.usecase.customer.create.CreateCustomerUseCase;
import com.project.domain.model.usecase.customer.get.FindCustomerByIdUseCase;
import com.project.domain.model.usecase.customer.get.GetAllCustomerUseCase;
import com.project.domain.model.usecase.customer.update.UpdateCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createUC;
    private final FindCustomerByIdUseCase findByIdUC;
    private final GetAllCustomerUseCase getAllUC;
    private final UpdateCustomerUseCase updateUC;

    @PostMapping(path = "/create")
    public Mono<ResponseEntity<Object>> createCustomer(@RequestBody CustomerEntity customer)
    {
        return createUC
                .saveCustomer(customer)
                .map(r -> ResponseEntity.ok().body((Object) r))
                .onErrorResume(BusinessException.class, err -> Mono.just(
                        ResponseEntity.badRequest().body(err.getBusinessErrorMessage())
                ));
    }

    @GetMapping("/{identification}")
    public Mono<ResponseEntity<Object>> findCustomerById(@PathVariable int identification) {
        return findByIdUC
                .getCustomerById(identification)
                .map(r -> ResponseEntity.ok().body((Object) r))
                .onErrorResume(BusinessException.class, err -> Mono.just(
                        ResponseEntity.notFound().build()
                ));
    }

    @GetMapping("/")
    public Flux<CustomerEntity> getAllCustomers()
    {
        return getAllUC
                .getAllCustomers();
    }

    @PutMapping("/")
    public Mono<ResponseEntity<Object>> updateCustomer(@RequestBody CustomerEntity customer)
    {
        return updateUC
                .updateCustomer(customer)
                .map(r -> ResponseEntity.ok().body((Object) r))
                .onErrorResume(BusinessException.class, err -> Mono.just(
                        ResponseEntity.status(404).body(err.getBusinessErrorMessage())
                ));
    }
}
