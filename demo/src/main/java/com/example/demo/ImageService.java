package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;


@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    // Save Image
    public ImageEntity saveImage(String base64Image) {
        // Check if the Base64 string contains the metadata part
        if (base64Image.contains(",")) {
            base64Image = base64Image.split(",")[1]; // Remove everything before the comma
        }

        byte[] imageData = Base64.getDecoder().decode(base64Image);
        ImageEntity image = new ImageEntity();
        image.setImageData(imageData);
        return imageRepository.save(image);
    }

    // Get Image
    public String getImage(Long id) {
        Optional<ImageEntity> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            byte[] imageData = imageOptional.get().getImageData();
            return Base64.getEncoder().encodeToString(imageData);
        }
        return null;
    }
}


// @Service
// public class ImageService {

//     @Autowired
//     private ImageRepository imageRepository;

//     // Save Image
//     public Image saveImage(String base64Image) {
//         byte[] imageData = Base64.getDecoder().decode(base64Image);
//         Image image = new Image();
//         image.setImageData(imageData);
//         return imageRepository.save(image);
//     }

//     // Get Image
//     public String getImage(Long id) {
//         Optional<Image> imageOptional = imageRepository.findById(id);
//         if (imageOptional.isPresent()) {
//             byte[] imageData = imageOptional.get().getImageData();
//             return Base64.getEncoder().encodeToString(imageData);
//         }
//         return null;
//     }
// }
    