import com.project.domain.model.entity.CustomerEntity;
import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.usecase.customer.get.FindCustomerByIdUseCase;
import com.project.domain.model.usecase.customer.get.GetAllCustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class GetAllCustomerUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private GetAllCustomerUseCase getAllCustomerUseCase;

    CustomerEntity cl1 = new CustomerEntity();
    CustomerEntity cl2 = new CustomerEntity();

    @BeforeEach
    void setup() {
         cl1 = CustomerEntity.builder()
                .identification(10415)
                .age(22)
                .dateCreate(LocalDate.of(2026, 5, 18))
                .phone(321L)
                .address("CRA 77 D # 110 A - 80")
                .build();

        cl2 = CustomerEntity.builder()
                .identification(104051)
                .age(22)
                .dateCreate(LocalDate.of(2026, 5, 18))
                .phone(321L)
                .address("CRA 77 D # 110 A - 80")
                .build();

    }

    @Test
    @DisplayName("You must find all saved clients")
    void shouldReturnAllCustomersWhenRepositoryContainsData(){

        when(customerRepository.getAllCustomers())
                .thenReturn(Flux.just(cl1, cl2));

        Flux<CustomerEntity> result = getAllCustomerUseCase.getAllCustomers();

        StepVerifier.create(result)
                .expectNextMatches(cl -> cl.getIdentification() == 10415)
                .expectNextMatches(cl -> cl.getIdentification() == 104051)
                .verifyComplete();

        verify(customerRepository).getAllCustomers();

    }

}
