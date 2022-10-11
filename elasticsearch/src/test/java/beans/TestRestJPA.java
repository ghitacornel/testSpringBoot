package beans;

import beans.model.Child;
import beans.model.Parent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestRestJPA {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;


    private Parent createSome() {

        Parent parent = new Parent();
        parent.setId(1);
        parent.setName("parent 1");

        Child child1 = new Child();
        child1.setId(1);
        child1.setName("child 1");
        child1.setParent(parent);

        Child child2 = new Child();
        child2.setId(2);
        child2.setName("child 2");
        child2.setParent(parent);

        parent.getChildren().add(child1);
        parent.getChildren().add(child2);
        return parent;
    }

    @Test
    public void testReadCreateReadUpdateDelete() throws Exception {

        // read
        mvc.perform(get("/parent"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        // create
        {

            mvc.perform(put("/parent")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(createSome())))
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
        }

        // read
        {
            mvc.perform(get("/parent/{id}", "1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(objectMapper.writeValueAsString(createSome())));
        }

        // delete
        mvc.perform(delete("/parent")).andExpect(status().isOk());

        // read
        mvc.perform(get("/parent"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

    }

}
