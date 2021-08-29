package lk.server.cocktails.features.image;

import lk.server.cocktails.utils.CreatorPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping()
public class ImageController {

    @Autowired
    private CreatorPhotoService photoService;

    @GetMapping(value = "/photo/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> photo(@PathVariable("name") String cocktailName) throws IOException {
        byte[] photo = photoService.getCocktailPhotoFromPath(cocktailName);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }
}
