package docker.controllers;

import docker.controller.PersonController;
import docker.model.PersonDto;
import docker.service.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @InjectMocks
    PersonController controller;

    @Mock
    PersonService service;

    @Test
    void findAll() {

        // given
        ArrayList<PersonDto> persons = new ArrayList<>();
        when(service.findAll()).thenReturn(persons);

        // when
        List<PersonDto> actual = controller.findAll();

        // then
        Assertions.assertThat(actual).isSameAs(persons);


        InOrder inOrder = Mockito.inOrder(service);
        inOrder.verify(service).findAll();
        inOrder.verifyNoMoreInteractions();

    }

}
