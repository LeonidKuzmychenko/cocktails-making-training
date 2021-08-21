package lk.server.cocktails.utils;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.locale.LocaleService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CreatorPhotoService {

    @Autowired
    private LocaleService localeService;

    public String getPathFromCocktail(Cocktail cocktail) {
        String photoPath = "photo/" + localeService.getEnString(cocktail.getCocktailName()).trim();
        photoPath = photoPath.replaceAll(" ", "***1***");
        photoPath = photoPath.replaceAll("'", "***2***");
        photoPath = photoPath.replaceAll("`", "***3***");
        photoPath = photoPath.replaceAll("’", "***4***");
        photoPath = photoPath.replaceAll("\\*", "***5***");
        photoPath = photoPath.replaceAll("#", "***6***");
        return photoPath;
    }

    public byte[] getCocktailPhotoFromPath(String cocktailName) throws IOException {
        cocktailName = cocktailName.replaceAll("\\*\\*\\*1\\*\\*\\*", " ");
        cocktailName = cocktailName.replaceAll("\\*\\*\\*2\\*\\*\\*", "'");
        cocktailName = cocktailName.replaceAll("\\*\\*\\*3\\*\\*\\*", "`");
        cocktailName = cocktailName.replaceAll("\\*\\*\\*4\\*\\*\\*", "’");
        cocktailName = cocktailName.replaceAll("\\*\\*\\*5\\*\\*\\*", "*");
        cocktailName = cocktailName.replaceAll("\\*\\*\\*6\\*\\*\\*", "#");
        InputStream in = getClass().getClassLoader().getResourceAsStream("icons/" + cocktailName + ".jpg");
        return IOUtils.toByteArray(in);
    }

}
