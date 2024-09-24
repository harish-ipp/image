// package com.example.demo;

// import com.example.demo.ImageEntity;
// import com.example.demo.ImageService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Optional;

// @RestController
// @RequestMapping("/api/images")
// public class ImageController {

//     @Autowired
//     private ImageService imageService;

//     // Upload Base64 Image
//     @PostMapping
//     public ResponseEntity<ImageEntity> uploadImage(@RequestBody String base64Image) {
//         ImageEntity savedImage = imageService.saveImage(base64Image);
//         return ResponseEntity.ok(savedImage); // Ensure the entire ImageEntity object is returned
//     }

//     // Retrieve Image by ID
//     @GetMapping("/{id}")
//     public ResponseEntity<String> getImage(@PathVariable Long id) {
//         Optional<ImageEntity> imageEntity = imageService.getImage(id);
//         return imageEntity.map(img -> ResponseEntity.ok(img.getImage()))
//                           .orElse(ResponseEntity.notFound().build());
//     }
// }



package com.example.demo;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/images")
    public class ImageController {

        @Autowired
        private ImageService imageService;

        @PostMapping("/upload")
        public ResponseEntity<String> uploadImage(@RequestBody String base64Image) {
            imageService.saveImage(base64Image);
            return ResponseEntity.ok("Image uploaded successfully!");
        }

        @GetMapping("/get/{id}")
        public ResponseEntity<String> getImage(@PathVariable Long id) {
            String base64Image = imageService.getImage(id);
            if (base64Image != null) {
                return ResponseEntity.ok(base64Image);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
