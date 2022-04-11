package beans.pagination;

import beans.Utils;
import beans.pagination.model.PageableDto;
import beans.pagination.model.SortDto;
import beans.pagination.repository.PageableEntityRepository;
import beans.pagination.repository.entity.PageableEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestPagination {

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mvc;

    @Autowired
    PageableEntityRepository repository;

    @BeforeEach
    public void before() {
        repository.deleteAll();
        for (int i = 0; i < 100; i++) {
            PageableEntity pageableEntity = new PageableEntity(i, "dummy data " + i);
            repository.save(pageableEntity);
        }
    }

    @Test
    public void testFindAll() throws Exception {
        String content = Utils.readFile("output/TestPagination_testFindAll.json");
        mvc.perform(get("/pageable/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }

    @Test
    public void testSort() throws Exception {
        String content = Utils.readFile("output/TestPagination_testSort.json");
        mvc.perform(post("/pageable/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new SortDto("data", "desc"))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }

    @Test
    public void testPage() throws Exception {
        String content = Utils.readFile("output/TestPagination_testPage.json");
        mvc.perform(post("/pageable/page")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PageableDto(2, 5, "data", "desc"))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }
}
