package kr.co.yanadoo.image.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.IntStream;

@SpringBootTest
class ImageServiceTest {

    @Autowired
    private ImageService service;

    @Autowired
    CacheManager cacheManager;

    private static final String filePath = "/Users/heowooyong/Downloads/";

    @Test
    void getImage() {

    }

    @Test
    void testGetImage() {

    }

    @Test
    void getResizeImage() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            try {
                service.getResizeImage(filePath, "money_3_1688632977933.png", 200 + (i * 10), 100 + (i * 10));
                System.out.println(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        for (String cacheName : cacheManager.getCacheNames()) {
            Cache<Object, Object> cache = ((CaffeineCache) Objects.requireNonNull(cacheManager.getCache(cacheName))).getNativeCache();
            CacheStats stats = cache.stats();
            System.out.println("cache '" + cacheName + "' - stats : " + stats.toString());
        }

        IntStream.rangeClosed(1, 10).forEach(i -> {
            try {
                service.getResizeImage(filePath, "money_3_1688632977933.png", 200 + (i * 10), 100 + (i * 10));
                System.out.println(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        for (String cacheName : cacheManager.getCacheNames()) {
            Cache<Object, Object> cache = ((CaffeineCache) Objects.requireNonNull(cacheManager.getCache(cacheName))).getNativeCache();
            CacheStats stats = cache.stats();
            System.out.println("cache '" + cacheName + "' - stats : " + stats.toString());
        }

    }
}