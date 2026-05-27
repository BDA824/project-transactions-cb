package customer;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.model.entity.CustomerEntity;
import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.usecase.customer.get.FindCustomerByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindCustomerByIdUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private FindCustomerByIdUseCase findCustomerByIdUseCase;

    CustomerEntity cl = new CustomerEntity();

    @BeforeEach
    void setup() {
        cl = CustomerEntity.builder()
                .identification(10415)
                .age(22)
                .dateCreate(LocalDate.of(2026, 5, 18))
                .phone(321L)
                .address("CRA 77 D # 110 A - 80")
                .build();
    }

    @Test
    @DisplayName("The customer must be found")
    void shouldFindCustomerById() {

        when(customerRepository.findCustomerById(10415))
                .thenReturn(Mono.just(cl));

        Mono<CustomerEntity> result = findCustomerByIdUseCase.getCustomerById(10415);

        StepVerifier.create(result)
                .expectNextMatches(cl ->
                        cl.getPhone() == 321L)
                .verifyComplete();

        verify(customerRepository).findCustomerById(10415);

    }

    @Test
    @DisplayName("The customer could not be found")
    void shouldShowTheErrorMessage() {

        when(customerRepository.findCustomerById(1040))
                .thenReturn(Mono.empty());

        Mono<CustomerEntity> result =findCustomerByIdUseCase.getCustomerById(1040);

        StepVerifier.create(result)
                .expectError(BusinessException.class)
                .verify();

        verify(customerRepository).findCustomerById(1040);
    }

}
