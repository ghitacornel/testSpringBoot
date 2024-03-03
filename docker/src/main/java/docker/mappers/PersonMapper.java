package docker.mappers;

import docker.model.CreatePersonDto;
import docker.model.PersonDto;
import docker.model.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    Person map(CreatePersonDto createPersonDto);

    PersonDto map(Person person);

    Person map(PersonDto personDto);

    List<PersonDto> map(List<Person> all);

}
