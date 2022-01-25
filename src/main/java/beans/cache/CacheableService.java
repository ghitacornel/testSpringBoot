package beans.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static beans.cache.CacheConfiguration.CACHE_NAME;

@Service
@CacheConfig(cacheNames = CACHE_NAME)
public class CacheableService {

    private final Map<Integer, CacheableModel> data = new HashMap<>();

    @CachePut(key = "#model.id")
    public CacheableModel addAlsoInCache(CacheableModel model) {
        data.put(model.getId(), model);
        return model;
    }

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

    // for test purpose
    private boolean cacheHit = false;

    public boolean isCacheHit() {
        return cacheHit;
    }

    public void setCacheHit(boolean cacheHit) {
        this.cacheHit = cacheHit;
    }
}
