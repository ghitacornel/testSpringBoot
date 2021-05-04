package beans.cache;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cache")
@AllArgsConstructor
public class CacheController {

    private final CacheableService service;

    @GetMapping
    public CacheableModel createWithId1() {
        CacheableModel cacheableModel = new CacheableModel(1, "value 1");
        service.add(cacheableModel);
        return cacheableModel;
    }

    @GetMapping(value = "/{id}")
    public CacheableModel findById(Integer id) {
        return service.findById(id);
    }

}
