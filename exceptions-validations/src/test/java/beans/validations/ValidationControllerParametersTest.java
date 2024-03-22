package beans.validations;

import beans.AbstractTestSpringBootContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ValidationControllerParametersTest extends AbstractTestSpringBootContext {

    private static final String URL = "/validateParameters";

    @Autowired
    MockMvc mvc;

    @Test
    public void testIsValid() throws Exception {
        mvc.perform(get(URL + "/6"))
                .andExpect(status().isOk())
                .andExpect(content().string("6"));
    }

    @Test
    public void testIsInvalid() throws Exception {
        mvc.perform(get(URL + "/4"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"invokeDirect.arg0\",\"message\":\"must be greater than or equal to 5\",\"messageCode\":\"{jakarta.validation.constraints.Min.message}\"}]"));
    }

    @Test
    public void testIsValidMultipleParameters() throws Exception {
        mvc.perform(get(URL + "/xxx/partialPath/yyy"))
                .andExpect(status().isOk())
                .andExpect(content().string("xxxyyy"));
    }

    @Test
    public void testIsValidMultipleParametersFirstOneBlank() throws Exception {

        mvc.perform(get(URL + "/ /partialPath/yyy"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"invokeDirect.arg0\",\"message\":\"must not be blank\",\"messageCode\":\"{jakarta.validation.constraints.NotBlank.message}\"}]"));

        mvc.perform(get(URL + "/xxx/partialPath/ "))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"invokeDirect.arg1\",\"message\":\"must not be blank\",\"messageCode\":\"{jakarta.validation.constraints.NotBlank.message}\"}]"));

        mvc.perform(get(URL + "//partialPath/yyy"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"type\":\"about:blank\",\"title\":\"Not Found\",\"status\":404,\"detail\":\"No static resource validateParameters/partialPath/yyy.\",\"instance\":\"/validateParameters/partialPath/yyy\"}"));

        mvc.perform(get(URL + "/xxx/partialPath/"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"type\":\"about:blank\",\"title\":\"Not Found\",\"status\":404,\"detail\":\"No static resource validateParameters/xxx/partialPath.\",\"instance\":\"/validateParameters/xxx/partialPath/\"}"));
    }

    @Test
    public void customParameterValue() throws Exception {
        mvc.perform(get(URL + "/customParameterValue/UP"))
                .andExpect(status().isOk())
                .andExpect(content().string("UP is OK"));
        mvc.perform(get(URL + "/customParameterValue/DOWN"))
                .andExpect(status().isOk())
                .andExpect(content().string("DOWN is OK"));
        mvc.perform(get(URL + "/customParameterValue/xxx"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"customParameterValue.arg0\",\"message\":\"only UP or DOWN accepted\",\"messageCode\":\"only UP or DOWN accepted\"}]"));
    }

}
