package beans.repository;

import beans.model.SimpleDataModelELK;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SimpleDataModelELKRepository extends ElasticsearchRepository<SimpleDataModelELK, Integer> {
    List<SimpleDataModelELK> findByContent(String content);
}
