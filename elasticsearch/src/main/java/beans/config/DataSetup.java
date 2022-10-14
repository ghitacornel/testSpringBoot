package beans.config;

import beans.model.Child;
import beans.model.Parent;
import beans.model.SimpleDataModel;
import beans.model.Status;
import beans.repository.ParentRepository;
import beans.repository.SimpleDataModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataSetup {

    private final ParentRepository parentRepository;
    private final SimpleDataModelRepository simpleDataModelRepository;

    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addInitialData() {
        {
            Parent parent = new Parent();
            parent.setId(1000);
            parent.setName("parent one");
            parent.setStatus(Status.NEW);
            parent.setContent("initial dummy data");
            {
                Child child = new Child();
                child.setId(2000);
                child.setName("anna");
                child.setContent("an apple for anna");
                child.setParent(parent);
                parent.getChildren().add(child);
            }
            parentRepository.save(parent);
        }
        {
            Parent parent = new Parent();
            parent.setId(1001);
            parent.setName("two");
            parent.setStatus(Status.ACTIVE);
            parent.setContent("some detailed content here");
            {
                Child child = new Child();
                child.setId(2001);
                child.setName("john");
                child.setContent("a pineapple for john");
                child.setParent(parent);
                parent.getChildren().add(child);
            }
            {
                Child child = new Child();
                child.setId(2002);
                child.setName("george");
                child.setContent("a melon for george");
                child.setParent(parent);
                parent.getChildren().add(child);
            }
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
            {
                Child child = new Child();
                child.setId(2003);
                child.setName("simon");
                child.setContent("a pear for simon");
                child.setParent(parent);
                parent.getChildren().add(child);
            }
            parentRepository.save(parent);
        }

        {
            SimpleDataModel simpleDataModel = new SimpleDataModel();
            simpleDataModel.setId(1);
            simpleDataModel.setName("a name");
            simpleDataModel.setContent("first content");
            simpleDataModelRepository.save(simpleDataModel);
        }
        {
            SimpleDataModel simpleDataModel = new SimpleDataModel();
            simpleDataModel.setId(2);
            simpleDataModel.setName("another name");
            simpleDataModel.setContent("some additional stuff");
            simpleDataModelRepository.save(simpleDataModel);
        }
        {
            SimpleDataModel simpleDataModel = new SimpleDataModel();
            simpleDataModel.setId(3);
            simpleDataModel.setName("john stuff");
            simpleDataModel.setContent("this name has some serious content");
            simpleDataModelRepository.save(simpleDataModel);
        }
    }
}
