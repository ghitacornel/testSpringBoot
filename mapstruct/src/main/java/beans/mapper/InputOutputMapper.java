package beans.mapper;

import beans.model.InputModel;
import beans.model.OutputModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
// componentModel = "spring" is redundant here due to plugin setting -Amapstruct.defaultComponentModel=spring
public interface InputOutputMapper {

    OutputModel map(InputModel inputModel);

    List<OutputModel> map(List<InputModel> inputModels);

}
