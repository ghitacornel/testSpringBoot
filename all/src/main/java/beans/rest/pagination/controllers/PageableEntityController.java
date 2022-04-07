package beans.rest.pagination.controllers;

import beans.rest.pagination.repositories.PageableEntity;
import beans.rest.pagination.services.PageableEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pageable")
@RequiredArgsConstructor
public class PageableEntityController {

    private final PageableEntityService service;

    @GetMapping("all")
    public List<PageableEntity> findAll() {
        return service.findAll();
    }

    @PostMapping("sort")
    public List<PageableEntity> findAll(@RequestBody SortDto sort) {
        return service.findAll(sort);
    }

    @PostMapping("page")
    public Page<PageableEntity> findAll(@RequestBody PageableDto pageable) {
        return service.findAll(pageable);
    }

}
