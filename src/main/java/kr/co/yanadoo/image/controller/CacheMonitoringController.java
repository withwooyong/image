package kr.co.yanadoo.image.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/cache-monitoring")
public class CacheMonitoringController {

    private final CacheManager cacheManager;

    @GetMapping(value = "/getCacheNames", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCacheNames() {
        log.info("getCacheNames");
        Collection<String> cacheNames = cacheManager.getCacheNames();
        return ResponseEntity.ok()
                .body(cacheNames);
    }

    @GetMapping(value = "/getAllKeyAndValue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllKeyAndValue() {
        log.info("getAllKeyAndValue");
        Map<String, Object> cacheMap = new HashMap<>();
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache<Object, Object> cache = ((CaffeineCache) Objects.requireNonNull(
                    cacheManager.getCache(cacheName))).getNativeCache();

            for (Object key : cache.asMap().keySet()) {
                Object value = cache.getIfPresent(key);
                log.info("key: " + key + " - value: " + value);
                cacheMap.put(key.toString(), value);
            }
        }
        return ResponseEntity.ok().body(cacheMap);

        // or functional
//        cacheManager.getCacheNames()
//                .stream()
//                .map(cacheName -> ((CaffeineCache) Objects.requireNonNull(cacheManager.getCache(cacheName))).getNativeCache())
//                .forEach(cache -> cache.asMap().keySet().forEach(key -> {
//                    System.out.println("key: " + key + " - value: " + Objects.requireNonNull(cache.getIfPresent(key)).toString());
//                }));
    }

    @GetMapping(value = "/getCacheStats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCacheStats() {
        log.info("getCacheStats");
        Map<String, Object> cacheMap = new HashMap<>();
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache<Object, Object> cache = ((CaffeineCache) Objects.requireNonNull(cacheManager.getCache(cacheName))).getNativeCache();
            CacheStats cacheStats = cache.stats();
            log.info("cache '" + cacheName + "' - stats : " + cacheStats.toString());
            cacheMap.put(cacheName, cacheStats.toString());
        }
        return ResponseEntity.ok().body(cacheMap);
    }
}
