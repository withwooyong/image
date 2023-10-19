package kr.co.yanadoo.image.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;

import java.util.Objects;

@SpringBootTest
class CacheConfigTest {

    @Autowired
    CacheManager cacheManager;

    // https://gngsn.tistory.com/170
    @Test
    public void getAllCaches() {
        cacheManager.getCacheNames().forEach(System.out::println);
    }

    @Test
    public void getAllKeyAndValue() {
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache<Object, Object> cache = ((CaffeineCache) Objects.requireNonNull(
                    cacheManager.getCache(cacheName))).getNativeCache();

            for (Object key : cache.asMap().keySet()) {
                Object value = cache.getIfPresent(key);
                assert value != null;
                System.out.println("key: " + key + " - value: " + value);
            }
        }

        // or functional
        cacheManager.getCacheNames()
                .stream()
                .map(cacheName -> ((CaffeineCache) Objects.requireNonNull(cacheManager.getCache(cacheName))).getNativeCache())
                .forEach(cache -> cache.asMap().keySet().forEach(key -> {
                    System.out.println("key: " + key + " - value: " + Objects.requireNonNull(cache.getIfPresent(key)).toString());
                }));
    }

    @Test
    public void getCachesStats() {
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache<Object, Object> cache = ((CaffeineCache) Objects.requireNonNull(cacheManager.getCache(cacheName))).getNativeCache();
            CacheStats stats = cache.stats();
            System.out.println("cache '" + cacheName + "' - stats : " + stats.toString());
        }
    }

    @Test
    public void removeAllCaches() {
        for (String cacheName : cacheManager.getCacheNames()) {
            Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
        }
    }

    @Test
    public void removeTargetCache() {
        String targetCacheName = "views";
        Objects.requireNonNull(cacheManager.getCache(targetCacheName)).clear();
    }

    @Test
    public void removeTargetKey() {
        String targetCacheName = "views";
        String targetCacheKey = "views_user4";
        Objects.requireNonNull(cacheManager.getCache(targetCacheName)).evict(targetCacheKey);
    }
}