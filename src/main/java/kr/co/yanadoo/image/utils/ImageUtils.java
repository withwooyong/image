package kr.co.yanadoo.image.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

    // convert BufferedImage to byte[]
    public static byte[] toByteArray(BufferedImage bufferedImage, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, baos);
        return baos.toByteArray();
    }

    // convert byte[] to BufferedImage
    public static BufferedImage toBufferedImage(byte[] bytes) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return ImageIO.read(inputStream);
    }

    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("/Users/heowooyong/Downloads/img_pc_main_slide01.png"));
        // convert BufferedImage to byte[]
        byte[] bytes = toByteArray(bufferedImage, "png");
        //encode the byte array for display purpose only, optional
        String bytesBase64 = Base64.encodeBase64String(bytes);
        System.out.println(bytesBase64);

        // decode byte[] from the encoded string
        byte[] bytesFromDecode = Base64.decodeBase64(bytesBase64);

        // convert the byte[] back to BufferedImage
        BufferedImage bufferedImage1 = toBufferedImage(bytesFromDecode);

        // save it somewhere
        ImageIO.write(bufferedImage1, "png", new File("/Users/heowooyong/Downloads/img_pc_main_slide01_1.png"));
    }
}
