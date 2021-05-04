package beans.cache;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cache")
@AllArgsConstructor
public class CacheController {

    private final CacheableService service;

    @GetMapping("/create")
    public CacheableModel createWithId1() {
        CacheableModel cacheableModel = new CacheableModel(1, "value 1");
        service.add(cacheableModel);
        return cacheableModel;
    }

    @GetMapping(value = "/{id}")
    public CacheableModel findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        service.removeById(id);
    }

}
