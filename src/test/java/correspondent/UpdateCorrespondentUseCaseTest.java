package correspondent;

import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.model.entity.CompensationEntity;
import com.project.domain.model.entity.CorrespondentEntity;
import com.project.domain.model.enums.CompensationStatus;
import com.project.domain.model.gateway.ICompensationRepository;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.usecase.correspondent.update.UpdateCorrespondentUseCase;
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
public class UpdateCorrespondentUseCaseTest {

    @Mock
    private ICorrespondentRepository correspondentRepository;

    @Mock
    private ICompensationRepository compensationRepository;

    @InjectMocks
    private UpdateCorrespondentUseCase updateCorrespondentUseCase;

    CorrespondentEntity crr = new CorrespondentEntity();
    CompensationEntity cmp = new CompensationEntity();

    @BeforeEach
    void setup(){
        crr = CorrespondentEntity.builder()
                .codeCB(14056)
                .id(1)
                .idCustomer(1040761483)
                .approvedAmount(172865.52)
                .averageTrx(45)
                .location("CRA 110 B")
                .type("Indirect")
                .owner("PTM")
                .lastClosed(175000)
                .build();

        cmp = CompensationEntity.builder()
                .codeCB(14056)
                .dateLimit(LocalDate.of(2026, 6,18))
                .totalValue(175000)
                .remainingValue(0)
                .state(CompensationStatus.PENDING)
                .build();
    }

    @Test
    @DisplayName("Should update correspondent successfully")
    void shouldUpdateCorrespondentSuccessfully(){

        when(correspondentRepository.updateCorrespondent(crr))
                .thenReturn(Mono.just(crr));

        when(compensationRepository.updateClosed(crr.getLastClosed(), crr.getCodeCB()))
                .thenReturn(Mono.just(cmp));

        Mono<CorrespondentEntity> result = updateCorrespondentUseCase.updateCorrespondent(crr);

        StepVerifier.create(result)
                .expectNextMatches(correspondent -> correspondent.getCodeCB() == 14056 &&
                        correspondent.getLastClosed() == 175000 &&
                        correspondent.getOwner().equals("PTM"))
                .verifyComplete();

        verify(correspondentRepository)
                .updateCorrespondent(crr);
        verify(compensationRepository)
                .updateClosed(crr.getLastClosed(), crr.getCodeCB());
    }

    @Test
    @DisplayName("Should throw a business exception when correspondent does not exist")
    void shouldThrowBusinessExceptionWhenCorrespondentDoesNotExist(){

        when(correspondentRepository.updateCorrespondent(crr))
                .thenReturn(Mono.empty());

        Mono<CorrespondentEntity> result = updateCorrespondentUseCase.updateCorrespondent(crr);

        StepVerifier.create(result)
                .expectError(BusinessException.class)
                .verify();

        verify(correspondentRepository)
                .updateCorrespondent(crr);
        verify(compensationRepository, never())
                .updateClosed(crr.getLastClosed(), crr.getCodeCB());
    }
}
