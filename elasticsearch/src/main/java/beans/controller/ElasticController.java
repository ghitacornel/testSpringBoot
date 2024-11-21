package beans.controller;


import beans.model.*;
import beans.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "elastic")
@RequiredArgsConstructor
public class ElasticController {

    private final ElasticSearchService service;

    @GetMapping(value = "/parent/{content}")
    public List<ParentELK> findParentByContent(@PathVariable(name = "content") String content) {
        return service.findParentByContent(content);
    }

    @GetMapping(value = "/parent/projection/{content}")
    public List<ParentELK> findParentProjectionByContent(@PathVariable(name = "content") String content) {
        return service.findParentProjectionByContent(content);
    }

    @GetMapping(value = "/parent/projection/nested/{content}")
    public List<ParentELK> findParentProjectionByNestedChildNameAndContent(@PathVariable(name = "content") String content) {
        return service.findParentProjectionByNestedChildNameAndContent(content);
    }

    @GetMapping(value = "/child/{content}")
    public List<ChildELK> findChildByContent(@PathVariable(name = "content") String content) {
        return service.findChildByContent(content);
    }

    @GetMapping(value = "/child/projection/{content}")
    public List<ChildELK> findChildProjectionByContent(@PathVariable(name = "content") String content) {
        return service.findChildProjectionByContent(content);
    }

    @GetMapping(value = "/simple/{content}")
    public List<SimpleDataModelELK> findSimpleByContent(@PathVariable(name = "content") String content) {
        return service.findSimpleByContent(content);
    }

}
