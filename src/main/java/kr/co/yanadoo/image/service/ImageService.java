package kr.co.yanadoo.image.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class ImageService {

//    private final ResourceLoader resourceLoader;

//    public BufferedImage getImage(String fileName) throws IOException {
//        log.info("getImage {}", fileName);
//        //        InputStream in = getClass().getResourceAsStream(fileName);
////        log.info("### getImageDynamicType in: {}", in);
//        // 이미지 파일 경로
//        // static/images/img_pc_main_slide01.png
////        String imageP = "/Users/heowooyong/IdeaProjects/demo/demo-h2db/src/main/java/com/example/demoh2db/controller/ImageController.java
//        String filePath = "classpath:static/images/" + fileName;
//        InputStream in = resourceLoader.getResource(filePath).getInputStream();
//        // 1920x920
//        String testImg = "/Users/heowooyong/Downloads/img_pc_main_slide01.png";
//        // 이미지 파일 읽기
//        File file = new File(testImg);
//        BufferedImage image = ImageIO.read(file);
//        // 이미지 정보 출력
//        int width = image.getWidth();
//        int height = image.getHeight();
//        log.info("{} X {}", width, height);
//        return image;
//    }

    public BufferedImage getBufferedImage(String filePath, String fileName) throws IOException {
        log.info("getBufferedImage {}, {}", filePath, fileName);
        File file = new File(filePath + fileName);
        BufferedImage bufferedImage = ImageIO.read(file);
        // 이미지 정보 출력
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        log.info("{} X {}", width, height);
        return bufferedImage;
    }

    public ByteArrayResource getByteArrayResource(String filePath, String fileName) throws IOException {
        log.info("getByteArrayResource {}, {}", filePath, fileName);

        // 이미지 파일을 읽어옴
        Path path = Paths.get(filePath + fileName);
        byte[] imageBytes = Files.readAllBytes(path);
        // 이미지 리소스 생성
        return new ByteArrayResource(imageBytes);
    }

    // https://jistol.github.io/spring/2017/02/09/springboot-cache-key/
//    @Cacheable(cacheNames = "imageStore", key = "#fileName + '::' + #width + '::' + #height", unless = "#result == null")
    @Cacheable(cacheNames = "imageStore", key = "#fileName + '::' + #width + '::' + #height")
    public byte[] getResizeImage(String filePath, String fileName, Integer width, Integer height) throws IOException {
        String originalFile = filePath + fileName;
        log.info("getResizeImage {}, {}, {}, {}", fileName, width, height, originalFile);
        // make width height null check
        if (Objects.nonNull(width) && Objects.nonNull(height)) {
            // resized image
            BufferedImage bufferedImage = Thumbnails.of(originalFile).size(width, height).outputFormat("png").asBufferedImage();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
            //.toFile(resizedFile);
        } else {
            // original image
            Path path = Paths.get(filePath + fileName);
            return Files.readAllBytes(path);
        }
    }
}
