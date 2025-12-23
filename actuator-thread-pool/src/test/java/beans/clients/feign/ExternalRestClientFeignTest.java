package beans.clients.feign;

import beans.clients.external.PersonResponseDto;
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

        PersonResponseDto outputModel = new PersonResponseDto(3, "output GET", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));

        Assertions.assertThat(contract.invokeGET("3", "4")).isEqualTo(outputModel);
    }

}
