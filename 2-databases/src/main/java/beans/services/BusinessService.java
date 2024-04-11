package beans.services;

import beans.config.input.entity.InputEntity;
import beans.config.input.repository.InputEntityRepository;
import beans.config.output.entity.OutputEntity;
import beans.config.output.repository.OutputEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessService {

    private final InputEntityRepository inputEntityRepository;
    private final OutputEntityRepository outputEntityRepository;

    public void play() {
        inputEntityRepository.save(InputEntity.builder()
                .firstName("firstName1")
                .lastName("lastName1")
                .build());
        outputEntityRepository.save(OutputEntity.builder()
                .firstName("firstName2")
                .lastName("lastName2")
                .build());
        log.info(String.valueOf(inputEntityRepository.findAll()));
        log.info(String.valueOf(outputEntityRepository.findAll()));
    }

}
