package beans.controller;


import beans.model.Child;
import beans.model.Parent;
import beans.model.SimpleDataModel;
import beans.projections.ChildProjection;
import beans.projections.ParentProjection;
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
    public List<Parent> findParentByContent(@PathVariable(name = "content") String content) {
        return service.findParentByContent(content);
    }

    @GetMapping(value = "/parent/projection/{content}")
    public List<ParentProjection> findParentProjectionByContent(@PathVariable(name = "content") String content) {
        return service.findParentProjectionByContent(content);
    }

    @GetMapping(value = "/child/{content}")
    public List<Child> findChildByContent(@PathVariable(name = "content") String content) {
        return service.findChildByContent(content);
    }

    @GetMapping(value = "/child/projection/{content}")
    public List<ChildProjection> findChildProjectionByContent(@PathVariable(name = "content") String content) {
        return service.findChildProjectionByContent(content);
    }

    @GetMapping(value = "/simple/{content}")
    public List<SimpleDataModel> findSimpleByContent(@PathVariable(name = "content") String content) {
        return service.findSimpleByContent(content);
    }

}
