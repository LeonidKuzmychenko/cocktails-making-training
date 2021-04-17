package lk.server.cocktails.features.image;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping()
public class ImageController {

    @ResponseBody
    @GetMapping(value = "/photo/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> photo(HttpServletRequest request, @PathVariable("name") String cocktailName) throws IOException {
        cocktailName = cocktailName.replaceAll("_", " ");
        cocktailName = cocktailName.replaceAll("\\*", "’");
        InputStream in = request.getServletContext().getResourceAsStream("icons/" + cocktailName + ".jpg");
        return new ResponseEntity<>(IOUtils.toByteArray(in), HttpStatus.OK);
    }
}
