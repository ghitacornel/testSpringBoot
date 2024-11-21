package beans.config;

import beans.model.Child;
import beans.model.Parent;
import beans.model.SimpleDataModel;
import beans.model.Status;
import beans.repository.ParentRepository;
import beans.repository.SimpleDataModelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class DataSetup {

    private final ParentRepository parentRepository;
    private final SimpleDataModelRepository simpleDataModelRepository;

    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addInitialData() {
        {
            Parent parent1 = new Parent();
            parent1.setId(1);
            parent1.setName("parent 1");
            parent1.setStatus(Status.NEW);
            parent1.setContent("initial dummy data");
            {
                Child child = new Child();
                child.setId(2000);
                child.setName("anna");
                child.setContent("an apple for anna");
                child.setParent(parent1);
                parent1.getChildren().add(child);
            }
            {
                Child child = new Child();
                child.setId(1991);
                child.setName("john");
                child.setContent("an apple for john");
                child.setParent(parent1);
                parent1.getChildren().add(child);
            }
            parentRepository.save(parent1);
        }

        {
            Parent parent2 = new Parent();
            parent2.setId(2);
            parent2.setName("parent 2");
            parent2.setStatus(Status.ACTIVE);
            parent2.setContent("some detailed content here, still dummy");
            {
                Child child = new Child();
                child.setId(2001);
                child.setName("john");
                child.setContent("first");
                child.setParent(parent2);
                parent2.getChildren().add(child);
            }
            {
                Child child = new Child();
                child.setId(2002);
                child.setName("george");
                child.setContent("first");
                child.setParent(parent2);
                parent2.getChildren().add(child);
            }
            {
                Child child = new Child();
                child.setId(2003);
                child.setName("george");
                child.setContent("last");
                child.setParent(parent2);
                parent2.getChildren().add(child);
            }
            parentRepository.save(parent2);
        }

        {
            Parent parent = new Parent();
            parent.setId(3);
            parent.setName("parent 3 no children");
            parent.setStatus(Status.ACTIVE);
            parent.setContent("more dummy stuff");
            parentRepository.save(parent);
        }
        {
            Parent parent = new Parent();
            parent.setId(4);
            parent.setName("parent 4 deleted");
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
