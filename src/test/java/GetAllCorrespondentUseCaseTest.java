import com.project.domain.model.entity.CorrespondentEntity;
import com.project.domain.model.gateway.ICorrespondentRepository;
import com.project.domain.model.usecase.correspondent.get.GetAllCorrespondentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetAllCorrespondentUseCaseTest {


    @Mock
    private ICorrespondentRepository correspondentRepository;

    @InjectMocks
    private GetAllCorrespondentUseCase getAllCorrespondentUseCase;

    CorrespondentEntity crr1 = new CorrespondentEntity();
    CorrespondentEntity crr2 = new CorrespondentEntity();

    @BeforeEach
    void setup(){

        crr1 = CorrespondentEntity.builder()
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

        crr2 = CorrespondentEntity.builder()
                .codeCB(16052)
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
    @DisplayName("Should found all correspondents registered")
    void shouldFoundAllCorrespondent(){

        when(correspondentRepository.getAllCorrespondents())
                .thenReturn(Flux.just(crr1, crr2));

        Flux<CorrespondentEntity> result = getAllCorrespondentUseCase.getAllCorrespondents();

        StepVerifier.create(result)
                .expectNextMatches(corr -> corr.getCodeCB() == 14056 &&
                        corr.getOwner().equals("PTM"))
                .expectNextMatches(corr -> corr.getCodeCB() == 16052 &&
                        corr.getOwner().equals("PTM"))
                .verifyComplete();

        verify(correspondentRepository)
                .getAllCorrespondents();
    }
}
