package kr.co.yanadoo.image.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

// https://myborn.tistory.com/24
@Log4j2
@Service
public class CaffeineCacheService {
    @Cacheable(cacheNames = "cacheStore", key = "#name")
    public String getCache(String name) throws InterruptedException {
        log.info("getCache {}", name);
//        sleep(3000);
        return name;
    }

    @CacheEvict(value = "cacheStore", key = "#name")
    public String removeCache(String name) {
        log.info("removeCache {}", name);
        return "OK";
    }
}