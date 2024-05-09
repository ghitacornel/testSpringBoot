package beans.clients.feign;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import beans.mock.MockServerSetup;
import feign.FeignException;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class ExternalRestClientFeignTest extends MockServerSetup {

    @Autowired
    FeignContract contract;

    @Test
    @SneakyThrows
    public void testPOST() {
        PersonRequestDto inputModel = new PersonRequestDto(1, "input POST", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));
        PersonResponseDto outputModel = new PersonResponseDto(2, "output POST", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));

        Assertions.assertThat(contract.invokePOST(inputModel)).isEqualTo(outputModel);
    }

    @Test
    @SneakyThrows
    public void testPATCH() {
        PersonRequestDto inputModel = new PersonRequestDto(1, "input PATCH", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));
        PersonResponseDto outputModel = new PersonResponseDto(2, "output PATCH", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));

        Assertions.assertThat(contract.invokePATCH(inputModel)).isEqualTo(outputModel);
    }

    @Test
    @SneakyThrows
    public void testGET() {

        PersonResponseDto outputModel = new PersonResponseDto(3, "output GET", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));

        Assertions.assertThat(contract.invokeGET("3", "4")).isEqualTo(outputModel);
    }

    @Test
    @SneakyThrows
    public void testGETBadData() {
        Assertions.assertThatThrownBy(() -> contract.invokeGETBadData()).isExactlyInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @SneakyThrows
    public void testGETNoContent() {
        Assertions.assertThatThrownBy(() ->contract.invokeGET("1111", "1111")).isExactlyInstanceOf(FeignException.NotFound.class);
    }

    @Test
    @SneakyThrows
    public void testSendBadData() {
        Assertions.assertThatThrownBy(() -> contract.invokePATCH(new PersonRequestDto(1, "", null, null))).isExactlyInstanceOf(ConstraintViolationException.class);
    }
}
