package beans;

import beans.etc.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.Server;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GrpcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Server server;

    @BeforeEach
    @SneakyThrows
    public void before() {
        server.start();
    }

    @AfterEach
    @SneakyThrows
    public void after() {
        server.shutdown();
    }

    @Test
    @SneakyThrows
    public void testFindAll() {
        mvc.perform(get("/invoke/grpc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(ResponseDto.builder()
                        .content("request body with response 111")
                        .status(true)
                        .build())));
    }

}
