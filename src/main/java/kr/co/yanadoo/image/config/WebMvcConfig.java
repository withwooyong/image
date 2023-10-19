package kr.co.yanadoo.image.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

// https://tbread-development.tistory.com/25
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    final String uploadPath = "/Users/heowooyong/Downloads";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        CacheControl cacheControl = CacheControl.noCache().mustRevalidate().cachePrivate().sMaxAge(Duration.ZERO);
        registry.addResourceHandler("/images/**")
//                .addResourceLocations("file:/home/ubuntu/image/");  // AWS EC2
//                .addResourceLocations("file:/root/image/")   // NAVER EC2
                .addResourceLocations(uploadPath)
                .setCacheControl(cacheControl);
    }
}