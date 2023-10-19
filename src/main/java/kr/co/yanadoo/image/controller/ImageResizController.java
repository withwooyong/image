package kr.co.yanadoo.image.controller;

public class ImageResizController {

//    @PostMapping("/resize")
//    public ResponseEntity<?> resizeImage(@RequestParam("file") MultipartFile file,
//                                         @RequestParam("width") int width,
//                                         @RequestParam("height") int height) {
//        try {
//            // Create a temporary file to store the uploaded image
//            File tempFile = File.createTempFile("temp", null);
//            file.transferTo(tempFile);
//
//            // Resize the image using Thumbnailator
//            File resizedFile = new File("path/to/resized/image.jpg");
//            Thumbnails.of(tempFile)
//                    .size(width, height)
//                    .toFile(resizedFile);
//
//            // Return the resized image file
//            return ResponseEntity.ok().body(resizedFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to resize image.");
//        }
//    }
}


//import net.coobird.thumbnailator.Thumbnails;

//public class ImageResizer {
//    public static void main(String[] args) {
//        try {
//            // Specify the path to the original image
//            String originalImagePath = "path/to/original/image.jpg";
//
//            // Specify the path to save the resized image
//            String resizedImagePath = "path/to/save/resized/image.jpg";
//
//            // Set the desired dimensions for the resized image
//            int targetWidth = 800;
//            int targetHeight = 600;
//
//            // Resize the image using Thumbnailator
//            Thumbnails.of(originalImagePath)
//                    .size(targetWidth, targetHeight)
//                    .toFile(resizedImagePath);
//
//            System.out.println("Image resized successfully!");
//        } catch (Exception e) {
//            System.out.println("Error occurred: " + e.getMessage());
//        }
//    }
//}
