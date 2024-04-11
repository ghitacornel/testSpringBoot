package docker.service;

import docker.mappers.PersonMapper;
import docker.model.CreatePersonDto;
import docker.model.PersonDto;
import docker.entities.Person;
import docker.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    public List<PersonDto> findAll() {
        return mapper.map(repository.findAll());
    }

    public PersonDto findById(Integer id) {
        return mapper.map(repository.getReferenceById(id));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void update(PersonDto personDto) {
        mapper.update(repository.getReferenceById(personDto.getId()), personDto);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public PersonDto create(CreatePersonDto createPersonDto) {
        Person person = mapper.map(createPersonDto);
        repository.save(person);
        return mapper.map(person);
    }
}
