package beans.cache;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import template.AbstractTestSpringBootContext;

public class TestCacheableService extends AbstractTestSpringBootContext {

    @Autowired
    CacheableService cacheableService;

    @Test
    public void testCacheMissHit() {

        CacheableModel cacheableModel = new CacheableModel(1, "unu");

        cacheableService.add(cacheableModel);

        {// first time fail
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assert.assertFalse(cacheableService.isCacheHit());
            Assert.assertEquals(cacheableModel, model);
        }

        {// second time cache hit
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assert.assertTrue(cacheableService.isCacheHit());
            Assert.assertEquals(cacheableModel, model);
        }

        {// third time cache hit
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assert.assertTrue(cacheableService.isCacheHit());
            Assert.assertEquals(cacheableModel, model);
        }

        // remove and evict automatically
        cacheableService.removeById(1);

        {// at last time cache miss
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assert.assertFalse(cacheableService.isCacheHit());
            Assert.assertNull(model);
        }

    }

    @Test
    public void testCacheAddThroughPutMissHit() {

        CacheableModel cacheableModel = new CacheableModel(1, "unu");

        // add and add in cache also
        cacheableService.addAlsoInCache(cacheableModel);

        {// first time fail
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assert.assertTrue(cacheableService.isCacheHit());
            Assert.assertEquals(cacheableModel, model);
        }

        {// second time cache hit
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assert.assertTrue(cacheableService.isCacheHit());
            Assert.assertEquals(cacheableModel, model);
        }

        {// third time cache hit
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assert.assertTrue(cacheableService.isCacheHit());
            Assert.assertEquals(cacheableModel, model);
        }

        // remove and evict automatically
        cacheableService.removeById(1);

        {// at last time cache miss
            cacheableService.setCacheHit(true);
            CacheableModel model = cacheableService.findById(1);
            Assert.assertFalse(cacheableService.isCacheHit());
            Assert.assertNull(model);
        }

    }
}
