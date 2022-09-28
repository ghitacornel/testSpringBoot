package beans.mapper;

import beans.model.InputModel;
import beans.model.OutputModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InputOutputMapper {

    OutputModel map(InputModel inputModel);

    List<OutputModel> map(List<InputModel> inputModels);

}
