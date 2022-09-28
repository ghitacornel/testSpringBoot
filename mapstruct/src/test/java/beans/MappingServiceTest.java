package beans;

import beans.model.InputModel;
import beans.model.OutputModel;
import beans.service.MappingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MappingServiceTest {

    @Autowired
    MappingService mappingService;

    @Test
    public void testMapping() {
        InputModel inputModel = InputModel.builder().id(1).name("name").build();

        OutputModel outputModel = mappingService.convert(inputModel);

        Assertions.assertEquals(inputModel.getId(), outputModel.getId());
        Assertions.assertSame(inputModel.getName(), outputModel.getName());
    }

    @Test
    public void testMappingList() {
        InputModel inputModel1 = InputModel.builder().id(1).name("name1").build();
        InputModel inputModel2 = InputModel.builder().id(2).name("name2").build();

        List<InputModel> list = List.of(inputModel1, inputModel2);
        List<OutputModel> outputModels = mappingService.convert(list);

        Assertions.assertEquals(inputModel1.getId(), outputModels.get(0).getId());
        Assertions.assertSame(inputModel1.getName(), outputModels.get(0).getName());
        Assertions.assertEquals(inputModel2.getId(), outputModels.get(1).getId());
        Assertions.assertSame(inputModel2.getName(), outputModels.get(1).getName());
    }
}
