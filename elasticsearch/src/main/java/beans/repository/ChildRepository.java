package beans.repository;

import beans.model.Child;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ChildRepository extends ElasticsearchRepository<Child, Integer> {
    List<Child> findByContent(String content);
}
