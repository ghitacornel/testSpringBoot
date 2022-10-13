package beans.service;

import beans.model.Parent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticSearchService {
    public List<Parent> findByContent(String content) {
        return new ArrayList<>();
    }
}
