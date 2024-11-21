package beans.service;

import beans.model.*;
import beans.repository.ChildELKRepository;
import beans.repository.ParentELKRepository;
import beans.repository.SimpleDataModelELKRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ElasticSearchService {

    private final ParentELKRepository parentELKRepository;
    private final ChildELKRepository childELKRepository;
    private final SimpleDataModelELKRepository simpleDataModelELKRepository;

    public List<ParentELK> findParentByContent(String content) {
        return parentELKRepository.findByContent(content);
    }

    public List<ParentELK> findParentProjectionByContent(String content) {
        return new ArrayList<>();
    }

    public List<ParentELK> findParentProjectionByNestedChildNameAndContent(String content) {
        return new ArrayList<>();
    }

    public List<ChildELK> findChildByContent(String content) {
        return childELKRepository.findByContent(content);
    }

    public List<ChildELK> findChildProjectionByContent(String content) {
        return new ArrayList<>();
    }

    public List<SimpleDataModelELK> findSimpleByContent(String content) {
        return simpleDataModelELKRepository.findByContent(content);
    }

}
