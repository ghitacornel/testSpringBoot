package docker.service;

import docker.mappers.PersonMapper;
import docker.model.CreatePersonDto;
import docker.model.PersonDto;
import docker.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    public List<PersonDto> findAll() {
        return mapper.map(repository.findAll());
    }

    public PersonDto findById(Integer id) {
        return repository.findById(id).map(mapper::map).orElseThrow(() -> new EntityNotFoundException("no person found for id = " + id));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void save(CreatePersonDto personDto) {
        repository.save(mapper.map(personDto));
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
