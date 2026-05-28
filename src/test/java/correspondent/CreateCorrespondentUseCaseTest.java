package correspondent;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.model.entity.CompensationEntity;
import com.project.domain.model.entity.CorrespondentEntity;
import com.project.domain.model.entity.CustomerEntity;
import com.project.domain.model.enums.CompensationStatus;
import com.project.domain.model.gateway.ICompensationRepository;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.gateway.ICustomerRepository;
import com.project.domain.model.usecase.correspondent.create.CreateCorrespondentUseCase;
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
public class CreateCorrespondentUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private ICorrespondentRepository correspondentRepository;

    @Mock
    private ICompensationRepository compensationRepository;

    @InjectMocks
    private CreateCorrespondentUseCase createCorrespondentUseCase;

    CustomerEntity cl = new CustomerEntity();
    CorrespondentEntity crr = new CorrespondentEntity();
    CompensationEntity cmp = new CompensationEntity();

    @BeforeEach
    void setup(){
        cl = CustomerEntity.builder()
                .identification(1040761483)
                .age(22)
                .dateCreate(LocalDate.of(2026, 5, 24))
                .address("CRA 77 D")
                .phone(321L)
                .build();

        crr = CorrespondentEntity.builder()
                .codeCB(14056)
                .id(1)
                .idCustomer(1040761483)
                .approvedAmount(172865.52)
                .averageTrx(45)
                .location("CRA 110 B")
                .type("Indirect")
                .owner("PTM")
                .lastClosed(0)
                .build();
        cmp = CompensationEntity.builder()
                .codeCB(14056)
                .dateLimit(LocalDate.of(2026, 6,18))
                .totalValue(0)
                .remainingValue(0)
                .state(CompensationStatus.PENDING)
                .build();
    }

    @Test
    @DisplayName("Should create correspondent successfully")
    void shouldCreateCorrespondentSuccessfully(){

        when(customerRepository.findCustomerById(1040761483))
                .thenReturn(Mono.just(cl));

        when(correspondentRepository.getCorrespondentById(14056))
                .thenReturn(Mono.empty());

        when(correspondentRepository.create(crr))
                .thenReturn(Mono.just(crr));

        when(compensationRepository.create(cmp))
                .thenReturn(Mono.just(cmp));

        Mono<CorrespondentEntity> result = createCorrespondentUseCase.createCB(crr);

        StepVerifier.create(result)
                .expectNextMatches(correspondent ->
                        correspondent.getCodeCB() == 14056 &&
                        correspondent.getIdCustomer() == 1040761483 &&
                        correspondent.getApprovedAmount() == 172865.52 &&
                        correspondent.getOwner().equals("PTM"))
                .verifyComplete();

        verify(customerRepository)
                .findCustomerById(1040761483);
        verify(correspondentRepository)
                .getCorrespondentById(14056);
        verify(correspondentRepository)
                .create(crr);
        verify(compensationRepository)
                .create(cmp);
    }

    @Test
    @DisplayName("Should throw a business exception when customer does not exist")
    void shouldThrowBusinessExceptionWhenCustomerDoesNotExist(){

        when(customerRepository.findCustomerById(1040761483))
                .thenReturn(Mono.empty());

        Mono<CorrespondentEntity> result = createCorrespondentUseCase.createCB(crr);

        StepVerifier.create(result)
                .expectError(BusinessException.class)
                .verify();

        verify(customerRepository)
                .findCustomerById(1040761483);
        verify(correspondentRepository, never())
                .getCorrespondentById(14056);
        verify(compensationRepository, never())
                .create(any(CompensationEntity.class));
        verify(correspondentRepository, never())
                .create(crr);

    }

    @Test
    @DisplayName("Should throw a business exception when correspondent already exists")
    void shouldThrowBusinessExceptionWhenCorrespondentAlreadyExists(){

        when(customerRepository.findCustomerById(1040761483))
                .thenReturn(Mono.just(cl));

        when(correspondentRepository.getCorrespondentById(14056))
                .thenReturn(Mono.just(crr));

        Mono<CorrespondentEntity> result = createCorrespondentUseCase.createCB(crr);

        StepVerifier.create(result)
                .expectError(BusinessException.class)
                .verify();

        verify(customerRepository)
                .findCustomerById(1040761483);
        verify(correspondentRepository)
                .getCorrespondentById(14056);
        verify(compensationRepository, never())
                .create(any(CompensationEntity.class));
        verify(correspondentRepository, never())
                .create(crr);
    }
}
