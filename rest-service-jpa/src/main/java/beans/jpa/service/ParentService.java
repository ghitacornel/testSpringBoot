package beans.jpa.service;

import beans.jpa.model.Child;
import beans.jpa.model.Parent;
import beans.jpa.repository.ChildRepository;
import beans.jpa.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;

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

    public Parent findById(Integer id) {
        return parentRepository.findById(id).get();
    }
}
