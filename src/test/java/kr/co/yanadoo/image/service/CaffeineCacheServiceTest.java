package kr.co.yanadoo.image.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class CaffeineCacheServiceTest {

    @Autowired
    private CaffeineCacheService service;

    @Test
    void getCache() {

        IntStream.rangeClosed(1, 20).forEach(i -> {
            try {
                System.out.println(service.getCache("test" + i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        IntStream.rangeClosed(1, 20).forEach(i -> {
            try {
                System.out.println(service.getCache("test" + i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void removeCache() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            System.out.println(service.removeCache("test" + i));
        });
    }
}