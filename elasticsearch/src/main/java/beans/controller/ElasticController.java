package beans.controller;


import beans.model.Parent;
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

    @GetMapping(value = "{content}")
    public List<Parent> findByContent(@PathVariable(name = "content") String content) {
        return service.findByContent(content);
    }

}
