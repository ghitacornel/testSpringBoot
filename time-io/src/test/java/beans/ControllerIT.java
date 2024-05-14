package beans;


import beans.model.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ControllerIT {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mvc;

    @Test
    public void testSort() throws Exception {

        Model request = Model.builder()
                .name("name")
                .localDate(LocalDate.now())
                .localDateTime(LocalDateTime.now())
                .localTime(LocalTime.now())
                .build();

        MvcResult mvcResult = mvc.perform(post("/format")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Model result = objectMapper.readValue(contentAsString, Model.class);

        Assertions.assertThat(result.getName()).isEqualTo(request.getName() + " altered");
        Assertions.assertThat(result.getLocalDate()).isEqualTo(request.getLocalDate().plusDays(1));
        Assertions.assertThat(result.getLocalDateTime()).isEqualTo(request.getLocalDateTime().plusDays(1));
        Assertions.assertThat(result.getLocalTime()).isEqualTo(request.getLocalTime().plusHours(1));

    }

}
