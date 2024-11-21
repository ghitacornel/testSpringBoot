package beans.repository;

import beans.model.ChildELK;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ChildELKRepository extends ElasticsearchRepository<ChildELK, Integer> {
    List<ChildELK> findByContent(String content);
}
