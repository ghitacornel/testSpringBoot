package cache;

import cache.simple.CacheableModel;
import cache.simple.CacheableService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCacheableService {

    @Autowired
    CacheableService cacheableService;

    @Test
    public void testCacheMissHit() {

        CacheableModel cacheableModel = new CacheableModel(1, "unu");

        cacheableService.add(cacheableModel);

        {// first time fail
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assertions.assertFalse(cacheableService.isCacheHit());
            Assertions.assertEquals(cacheableModel, model);
            Assertions.assertEquals(cacheableModel, model);
        }

        {// second time cache hit
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assertions.assertTrue(cacheableService.isCacheHit());
            Assertions.assertEquals(cacheableModel, model);
        }

        {// third time cache hit
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assertions.assertTrue(cacheableService.isCacheHit());
            Assertions.assertEquals(cacheableModel, model);
        }

        // remove and evict automatically
        cacheableService.removeById(1);

        {// at last time cache miss
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assertions.assertFalse(cacheableService.isCacheHit());
            Assertions.assertNull(model);
        }

    }

}
