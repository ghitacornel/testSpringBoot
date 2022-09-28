package beans.service;

import beans.mapper.InputOutputMapper;
import beans.model.InputModel;
import beans.model.OutputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MappingService {

    private final InputOutputMapper mapper;

    public OutputModel convert(InputModel inputModel) {
        return mapper.map(inputModel);
    }

    public List<OutputModel> convert(List<InputModel> inputModel) {
        return mapper.map(inputModel);
    }
}
