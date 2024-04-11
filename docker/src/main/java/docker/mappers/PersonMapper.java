package docker.mappers;

import docker.model.CreatePersonDto;
import docker.model.PersonDto;
import docker.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    Person map(CreatePersonDto createPersonDto);

    PersonDto map(Person person);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Person person, PersonDto personDto);

    List<PersonDto> map(List<Person> all);

}
