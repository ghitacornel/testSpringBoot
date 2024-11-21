package beans.service;

import beans.model.*;
import beans.repository.ChildRepository;
import beans.repository.ParentRepository;
import beans.repository.SimpleDataModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ElasticSearchService {

    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;
    private final SimpleDataModelRepository simpleDataModelRepository;

    public List<Parent> findParentByContent(String content) {
        return parentRepository.findByContent(content);
    }

    public List<Child> findChildByContent(String content) {
        return childRepository.findByContent(content);
    }

    public List<SimpleDataModel> findSimpleByContent(String content) {
        return simpleDataModelRepository.findByContent(content);
    }

}
