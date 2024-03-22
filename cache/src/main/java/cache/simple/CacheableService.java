package cache.simple;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static cache.simple.CacheConfiguration.CACHE_NAME;

@Service
@CacheConfig(cacheNames = CACHE_NAME)
public class CacheableService {

    private final Map<Integer, CacheableModel> data = new HashMap<>();

    public void add(CacheableModel model) {
        data.put(model.getId(), model);
    }

    @Cacheable
    public CacheableModel findById(Integer id) {
        cacheHit = false;
        return data.get(id);
    }

    @CacheEvict
    public void removeById(Integer id) {
        data.remove(id);
    }

    @Getter
    @Setter
    // for test purpose
    private boolean cacheHit = false;

}
