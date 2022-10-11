package beans.service;

import beans.model.Parent;
import beans.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ParentService {

    private final ParentRepository parentRepository;

    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    public void deleteAll() {
        parentRepository.deleteAll();
    }

    public Parent findById(Integer id) {
        Parent parent = parentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Parent not found for id " + id));
        parent.getChildren().size();// force proxy
        return parent;
    }

    public void createParent(Parent parent) {
        parent.getChildren().forEach(child -> child.setParent(parent));
        parentRepository.save(parent);
    }
}
