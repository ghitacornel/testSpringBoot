package beans.clients.feign;

import beans.clients.model.ClientResponseDto;
import beans.mock.MockServerSetup;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class ExternalRestClientFeignTest extends MockServerSetup {

    @Autowired
    FeignContract contract;

    @Test
    @SneakyThrows
    public void testGET() {

        ClientResponseDto outputModel = new ClientResponseDto(3, "output GET");

        Assertions.assertThat(contract.invokeGET("3", "4")).isEqualTo(outputModel);
    }

}
