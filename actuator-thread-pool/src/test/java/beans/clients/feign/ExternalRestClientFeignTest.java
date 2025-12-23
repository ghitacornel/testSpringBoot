package beans.clients.feign;

import beans.clients.model.ClientResponseDto;
import beans.mock.MockServerSetup;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExternalRestClientFeignTest extends MockServerSetup {

    @Autowired
    FeignContract contract;

    @Test
    @SneakyThrows
    public void testGET() {
        Assertions.assertThat(contract.invoke("ok")).isEqualTo(new ClientResponseDto("OK GET dummy content"));
    }

}
