package kr.co.yanadoo.image.controller;

import kr.co.yanadoo.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 이미지 리사이징 참고 하기위해 샘플 만들고 Cacheable 적용
 * https://myborn.tistory.com/24
 * https://spring.io/guides/gs/caching/
 */
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;


    private static final String filePath = "/Users/heowooyong/Downloads/";

    @GetMapping(value = "/buffered-image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getBufferedImage(@PathVariable String fileName) throws IOException {
        log.info("### getImage fileName {}", fileName);
        BufferedImage bufferedImage = imageService.getBufferedImage(filePath, fileName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return ResponseEntity.ok().body(bytes);
//                .body(new InputStreamResource(in));
    }

    // http://localhost:8080/image/byte-array-resource/allinone_main.png
    @GetMapping(value = "/byte-array-resource/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<ByteArrayResource> getByteArrayResource(@PathVariable String fileName) throws IOException {
        log.info("getByteArrayResource fileName {}", fileName);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
        ByteArrayResource imageBytes = imageService.getByteArrayResource(filePath, fileName);
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
        return ResponseEntity.ok().body(imageBytes);
//        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }

    // // https://github.com/coobird/thumbnailator/wiki/Examples
    // 1920x920
    // http://localhost:8080/image/resize-image-bytes/allinone_main.png
    // http://localhost:8080/image/resize-image-bytes/allinone_main.png&width=160&height=160
    @GetMapping(value = "/resize-image-bytes/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getResizeImageBytes(@PathVariable String fileName,
                                                      @RequestParam(required = false) Integer width,
                                                      @RequestParam(required = false) Integer height) throws IOException {
        log.info("getResizeImageBytes fileName: {}, width: {}, height: {}", fileName, width, height);
        byte[] imageBytes = imageService.getResizeImage(filePath, fileName, width, height);
        return ResponseEntity.ok().body(imageBytes);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }
}
