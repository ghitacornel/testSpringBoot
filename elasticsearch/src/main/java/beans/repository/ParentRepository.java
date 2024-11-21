package beans.repository;

import beans.model.Parent;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ParentRepository extends ElasticsearchRepository<Parent, Integer> {
    List<Parent> findByContent(String content);
}
