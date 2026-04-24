package com.project.infrastructure.entry_point;

import com.project.domain.model.entity.customerEntity;
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
    public Mono<ResponseEntity<customerEntity>> createCustomer(@RequestBody customerEntity customer)
    {
        return createUC
                .saveCustomer(customer)
                .map(r -> ResponseEntity.ok().body(r));
    }

    @GetMapping("/{identification}")
    public Mono<ResponseEntity<customerEntity>> findCustomerById(@PathVariable int identification)
    {
        return findByIdUC
                .getCustomerById(identification)
                .map(r -> ResponseEntity.ok().body(r));
    }

    @GetMapping("/")
    public Flux<customerEntity> getAllCustomers()
    {
        return getAllUC
                .getAllCustomers();
    }

    @PutMapping("/")
    public Mono<ResponseEntity<customerEntity>> updateCustomer(@RequestBody customerEntity customer)
    {
        return updateUC
                .updateCustomer(customer)
                .map(r -> ResponseEntity.ok().body(r));
    }
}
