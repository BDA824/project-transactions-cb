import com.project.domain.exception.exception_classes.BusinessException;
import com.project.domain.model.entity.CorrespondentEntity;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.usecase.correspondent.get.GetCorrespondentByCodeCbUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetCorrespondentByCodeCbUseCaseTest {

    @Mock
    private ICorrespondentRepository correspondentRepository;

    @InjectMocks
    private GetCorrespondentByCodeCbUseCase getCorrespondentByCodeCbUseCase;

    CorrespondentEntity crr = new CorrespondentEntity();

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
                .lastClosed(0)
                .build();
    }

    @Test
    @DisplayName("Should return correspondent when CodeCB exists")
    void shouldReturnCorrespondentWhenCodeCbExists(){

        when(correspondentRepository.getCorrespondentById(14056))
                .thenReturn(Mono.just(crr));

        Mono<CorrespondentEntity> result = getCorrespondentByCodeCbUseCase.getCorrespondentById(14056);

        StepVerifier.create(result)
                .expectNextMatches(correspondent -> correspondent.getCodeCB() == 14056 &&
                        correspondent.getIdCustomer() == 1040761483 &&
                        correspondent.getOwner().equals("PTM"))
                .verifyComplete();

        verify(correspondentRepository)
                .getCorrespondentById(14056);
    }

    @Test
    @DisplayName("Should throw a business exception when CodeCB does not exist")
    void shouldThrowBusinessExceptionWhenCodeCbDoesNotExist(){

        when(correspondentRepository.getCorrespondentById(14056))
                .thenReturn(Mono.empty());

        Mono<CorrespondentEntity> result = getCorrespondentByCodeCbUseCase.getCorrespondentById(14056);

        StepVerifier.create(result)
                .expectError(BusinessException.class)
                .verify();

        verify(correspondentRepository)
                .getCorrespondentById(14056);
    }
}
