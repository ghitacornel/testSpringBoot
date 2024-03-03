package docker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import docker.model.CreatePersonDto;
import docker.model.PersonDto;
import docker.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PersonControllerIT {

    @Autowired
    MockMvc mvc;

    @Autowired
    PersonRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testReadCreateReadUpdateDelete() throws Exception {

        // read all
        mvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        CreatePersonDto createPersonDto = CreatePersonDto.builder()
                .name("John")
                .dateOfBirth(LocalDateTime.of(2024, 3, 4, 11, 22))
                .build();
        PersonDto personDto;

        // create
        {
            MvcResult mvcResult = mvc.perform(post("/person")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(createPersonDto)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andReturn();
            personDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PersonDto.class);
            Assertions.assertThat(personDto.getId()).isNotNull();
            Assertions.assertThat(personDto.getName()).isEqualTo(createPersonDto.getName());
            Assertions.assertThat(personDto.getDateOfBirth()).isEqualTo(createPersonDto.getDateOfBirth());
        }

        // read
        {
            mvc.perform(get("/person/{id}", personDto.getId()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(objectMapper.writeValueAsString(personDto)));
        }

        personDto.setName("Doe");

        // update
        {
            mvc.perform(put("/person")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(personDto)))
                    .andExpect(status().isNoContent());
        }

        // read
        {
            mvc.perform(get("/person/{id}", personDto.getId()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(objectMapper.writeValueAsString(personDto)));
        }

        // delete
        mvc.perform(delete("/person/{id}", personDto.getId()))
                .andExpect(status().isNoContent());

        // read all
        mvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        // read
        mvc.perform(get("/person/{id}", personDto.getId()))
                .andExpect(status().isNotFound())
                .andExpect(content().string("no person found for id = 0"));

    }

}
