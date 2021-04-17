package lk.server.cocktails.features.image;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping()
public class ImageController {

    @ResponseBody
    @RequestMapping(value = "/photo/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photo(HttpServletRequest request, @PathVariable("name") String cocktailName) throws IOException {
        cocktailName = cocktailName.replaceAll("_", " ");
        cocktailName = cocktailName.replaceAll("\\*", "’");
        InputStream in = request.getServletContext().getResourceAsStream("/icons/" + cocktailName + ".jpg");
        return IOUtils.toByteArray(in);
    }
}
