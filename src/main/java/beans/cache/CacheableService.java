package beans.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheableService {

    private final Map<Integer, CacheableModel> data = new HashMap<>();

    @CachePut(value = "address_cache", key = "#model.id")
    public void addAlsoInCache(CacheableModel model) {
        data.put(model.getId(), model);
    }

    public void add(CacheableModel model) {
        data.put(model.getId(), model);
    }

    @Cacheable(value = "address_cache")
    public CacheableModel findById(Integer id) {
        cacheHit = false;
        return data.get(id);
    }

    @CacheEvict(value = "address_cache")
    public void removeById(Integer id) {
        data.remove(id);
    }

    private boolean cacheHit = false;

    public boolean isCacheHit() {
        return cacheHit;
    }

    public void setCacheHit(boolean cacheHit) {
        this.cacheHit = cacheHit;
    }
}
