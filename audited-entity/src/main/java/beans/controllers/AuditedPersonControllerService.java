package beans.controllers;

import beans.entities.AuditedPerson;
import beans.repositories.AuditedPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Service
@Transactional
@RequiredArgsConstructor
class AuditedPersonControllerService {

    private final AuditedPersonRepository repository;

    PersonResponse createDummy() {
        repository.deleteAll();
        AuditedPerson auditedPerson = AuditedPerson.builder()
                .id(1L)
                .name("dummy name")
                .salary(100.10)
                .build();
        repository.save(auditedPerson);
        AuditedPerson person = repository.findById(auditedPerson.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .salary(person.getSalary())
                .createdBy(person.getCreatedBy())
                .createdDate(person.getCreatedDate())
                .modifiedBy(person.getModifiedBy())
                .modifiedDate(person.getModifiedDate())
                .build();
    }

    PersonResponse updateDummy() {
        AuditedPerson person = repository.findById(1L).orElseThrow(() -> new RuntimeException("Not Found"));
        person.setSalary(person.getSalary() * 2);

        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .salary(person.getSalary())
                .createdBy(person.getCreatedBy())
                .createdDate(person.getCreatedDate())
                .modifiedBy(person.getModifiedBy())
                .modifiedDate(person.getModifiedDate())
                .build();
    }

    PersonResponse getDummy() {
        AuditedPerson person = repository.findById(1L).orElseThrow(() -> new RuntimeException("Not Found"));
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .salary(person.getSalary())
                .createdBy(person.getCreatedBy())
                .createdDate(person.getCreatedDate())
                .modifiedBy(person.getModifiedBy())
                .modifiedDate(person.getModifiedDate())
                .build();
    }
}
