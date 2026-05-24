import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.exception.message.BusinessErrorMessage;
import com.project.domain.model.entity.CustomerEntity;
import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.usecase.customer.create.CreateCustomerUseCase;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CreateCustomerUseCase createCustomerUseCase;

    CustomerEntity cl = new CustomerEntity();
    CustomerEntity clDuplicate = new CustomerEntity();
    @BeforeEach
    void setup(){
            cl = CustomerEntity.builder()
                .identification(1040761483)
                .age(22)
                .dateCreate(LocalDate.now())
                .phone(321L)
                .address("CRA 77 D")
                .build();

        clDuplicate = CustomerEntity.builder()
                .identification(1040761483)
                .age(22)
                .dateCreate(LocalDate.now())
                .phone(321L)
                .address("CRA 77 D")
                .build();
    }

    @Test
    @DisplayName("You must successfully create the customer")
    void shouldCreateCustomerWithSuccess() {

        when(customerRepository.findCustomerById(1040761483))
                .thenReturn(Mono.empty());

        when(customerRepository.create(cl))
                .thenReturn(Mono.just(cl));


        Mono<CustomerEntity> result = createCustomerUseCase.saveCustomer(cl);

        StepVerifier.create(result)
                .expectNextMatches(saved ->
                        saved.getIdentification() == 1040761483)
                .verifyComplete();

        verify(customerRepository).create(cl);
    }

    @Test
    @DisplayName("Don't create the user for that already exist")
    void shouldShowErrorMessage() {

        when(customerRepository.findCustomerById(cl.getIdentification()))
                .thenReturn(Mono.just(cl));


        Mono<CustomerEntity> result = createCustomerUseCase.saveCustomer(cl);

        StepVerifier.create(result)
                .expectError(BusinessException.class)
                .verify();


        verify(customerRepository, times(0)).create(any(CustomerEntity.class));
    }
}
