package beans.repository;

import beans.model.SimpleDataModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SimpleDataModelRepository extends ElasticsearchRepository<SimpleDataModel, Integer> {
    List<SimpleDataModel> findByContent(String content);
}
