package docker.mappers;

import docker.model.CreatePersonDto;
import docker.model.PersonDto;
import docker.model.entities.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {

    Person map(CreatePersonDto createPersonDto);

    PersonDto map(Person person);

    List<PersonDto> map(List<Person> all);

}
