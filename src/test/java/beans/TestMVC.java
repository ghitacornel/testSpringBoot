package beans;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestMVC extends AbstractTestSpringBootContext {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testController() throws Exception {
        String content = readFile("testMVC.html");
        mvc.perform(get("/custom?name=ion")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(content));
    }

    static String readFile(String fileName) {
        try {
            Path resourceDirectory = Paths.get("src", "test", "resources");
            String absolutePath = resourceDirectory.toFile().getAbsolutePath();
            byte[] encoded = Files.readAllBytes(Paths.get(absolutePath, fileName));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
