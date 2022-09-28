package cache.simple;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
// defaults org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
// defaults org.springframework.cache.concurrent.ConcurrentMapCacheManager
// currently overridden as an example with Caffeine
public class CacheConfiguration {

    public static final String CACHE_NAME = "cache_name";

    private CacheManager cacheManager;// for test purposes only

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .weakKeys()
                .recordStats();
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager(CACHE_NAME);
        caffeineCacheManager.setCaffeine(caffeine);
        cacheManager = caffeineCacheManager;// for test purposes only
        return caffeineCacheManager;
    }

    // for test purposes only
    @PreDestroy
    public void close() {
        CacheStats stats = ((CaffeineCache) cacheManager.getCache(CACHE_NAME)).getNativeCache().stats();
        System.err.println(stats);
    }

}
