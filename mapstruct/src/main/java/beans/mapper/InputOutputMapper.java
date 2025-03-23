package beans.mapper;

import beans.model.InputModel;
import beans.model.OutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface InputOutputMapper {

    OutputModel map(InputModel inputModel);

    List<OutputModel> map(List<InputModel> inputModels);

}
