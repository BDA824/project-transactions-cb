import com.project.domain.model.entity.CorrespondentEntity;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.usecase.correspondent.get.GetCorrespondentByIdUseCase;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetCorrespondentByIdUseCaseTest {

    @Mock
    private ICorrespondentRepository correspondentRepository;

    @InjectMocks
    private GetCorrespondentByIdUseCase getCorrespondentByIdUseCase;

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
    @DisplayName("Should return correspondent when customer ID exists")
    void shouldReturnCorrespondentWhenCustomerIdExists(){

        when(correspondentRepository.findCorrespondentById(1040761483))
                .thenReturn(Flux.just(crr));

        Flux<CorrespondentEntity> result = getCorrespondentByIdUseCase.getCorrespondentByIdCustomer(1040761483);

        StepVerifier.create(result)
                .expectNextMatches(correspondent ->
                        correspondent.getCodeCB() == 14056 &&
                                correspondent.getIdCustomer() == 1040761483 &&
                                correspondent.getApprovedAmount() == 172865.52 &&
                                correspondent.getOwner().equals("PTM"))
                .verifyComplete();

        verify(correspondentRepository)
                .findCorrespondentById(1040761483);
    }

}
