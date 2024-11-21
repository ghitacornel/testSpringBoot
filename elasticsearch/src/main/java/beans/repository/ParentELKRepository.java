package beans.repository;

import beans.model.ParentELK;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ParentELKRepository extends ElasticsearchRepository<ParentELK, Integer> {
    List<ParentELK> findByContent(String content);
}
