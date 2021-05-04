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

    @GetMapping(value = "/{id}")
    public CacheableModel findById(Integer id) {
        return service.findById(id);
    }

}
