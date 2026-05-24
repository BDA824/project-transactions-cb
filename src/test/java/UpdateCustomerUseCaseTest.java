import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.model.entity.CustomerEntity;
import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.usecase.customer.update.UpdateCustomerUseCase;
import org.junit.jupiter.api.BeforeAll;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateCustomerUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private UpdateCustomerUseCase updateCustomerUseCase;

    CustomerEntity cl = new CustomerEntity();
    CustomerEntity clUpdate =  new CustomerEntity();

    @BeforeEach
    void setup(){

        cl = CustomerEntity.builder()
                .identification(1040761483)
                .age(22)
                .dateCreate(LocalDate.of(2026, 5, 18))
                .phone(321L)
                .address("CRA 77 D # 110 A - 80")
                .build();

        clUpdate = CustomerEntity.builder()
                .identification(1040761483)
                .age(22)
                .dateCreate(LocalDate.of(2026, 5, 18))
                .phone(322L)
                .address("CRA 77 D # 115 A - 80")
                .build();
    }

    @Test
    @DisplayName("Should update an existing customer successfully")
    void shouldUpdateCustomerInformationSuccessfully() {

        when(customerRepository.findCustomerById(1040761483))
                .thenReturn(Mono.just(cl));

        when(customerRepository.updateCustomer(clUpdate))
                .thenReturn(Mono.just(clUpdate));

        Mono<CustomerEntity> result = updateCustomerUseCase.updateCustomer(clUpdate);

        StepVerifier.create(result)
                .expectNextMatches(customerUpdate -> customerUpdate.getPhone() == 322L &&
                        customerUpdate.getAddress().equals("CRA 77 D # 115 A - 80"))
                .verifyComplete();

        verify(customerRepository, times(1)).updateCustomer(clUpdate);
    }

    @Test
    @DisplayName("Should throw a business exception when customer is not found")
    void shouldThrowBusinessExceptionWhenCustomerIsNotFound(){

        when(customerRepository.findCustomerById(1040761482))
                .thenReturn(Mono.empty());

        clUpdate.setIdentification(1040761482);
        Mono<CustomerEntity> result = updateCustomerUseCase.updateCustomer(clUpdate);

        StepVerifier.create(result)
                .expectError(BusinessException.class)
                .verify();

        verify(customerRepository, times(1)).findCustomerById(1040761482);
        verify(customerRepository, never())
                .updateCustomer(any(CustomerEntity.class));

    }

}
