package beans.controller;

import beans.service.ItemService;
import beans.service.model.ItemModel;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("item")
public class ItemRestController {

    private final ItemService service;

    @GetMapping
    public List<ItemModel> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ItemModel findAll(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

}
