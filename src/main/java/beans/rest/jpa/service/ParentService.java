package beans.rest.jpa.service;

import beans.rest.jpa.model.Child;
import beans.rest.jpa.model.Parent;
import beans.rest.jpa.repository.ChildRepository;
import beans.rest.jpa.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {

    @Autowired
    ParentRepository parentRepository;

    @Autowired
    ChildRepository childRepository;

    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    public void createSome() {

        Parent parent = new Parent();
        parent.setId(1);
        parent.setName("parent 1");
        parentRepository.save(parent);

        Child child1 = new Child();
        child1.setId(1);
        child1.setName("child 1");
        child1.setParent(parent);
        childRepository.save(child1);

        Child child2 = new Child();
        child2.setId(2);
        child2.setName("child 2");
        child2.setParent(parent);
        childRepository.save(child2);

    }

    public void deleteAll() {
        childRepository.deleteAll();
        parentRepository.deleteAll();
    }
}
