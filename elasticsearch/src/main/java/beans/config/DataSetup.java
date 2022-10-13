package beans.config;

import beans.model.Parent;
import beans.model.Status;
import beans.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DataSetup {

    private final ParentRepository parentRepository;

    @PostConstruct
    public void addInitialData() {
        {
            Parent parent = new Parent();
            parent.setId(1000);
            parent.setName("parent one");
            parent.setStatus(Status.NEW);
            parent.setContent("initial dummy data");
            parentRepository.save(parent);
        }
        {
            Parent parent = new Parent();
            parent.setId(1001);
            parent.setName("two");
            parent.setStatus(Status.ACTIVE);
            parent.setContent("some detailed content here");
            parentRepository.save(parent);
        }
        {
            Parent parent = new Parent();
            parent.setId(1002);
            parent.setName("third one");
            parent.setStatus(Status.ACTIVE);
            parent.setContent("more dummy stuff");
            parentRepository.save(parent);
        }
        {
            Parent parent = new Parent();
            parent.setId(1003);
            parent.setName("deleted parent");
            parent.setStatus(Status.DELETED);
            parent.setContent("old content/data is removed");
            parentRepository.save(parent);
        }
    }
}
