package lk.server.cocktails.utils;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.locale.LocaleService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CreatorPhotoService {

    @Autowired
    private LocaleService localeService;

    public String getPathFromCocktail(Cocktail cocktail) {
        String photoPath = "photo/" + localeService.getEnString(cocktail.getCocktailName()).trim();
        photoPath = photoPath.replaceAll(" ", "_");
        photoPath = photoPath.replaceAll("'", "-0001-");
        photoPath = photoPath.replaceAll("`", "-0010-");
        photoPath = photoPath.replaceAll("’", "-0011-");
        photoPath = photoPath.replaceAll("\\*", "-0100-");
        photoPath = photoPath.replaceAll("#", "-0101-");
        return photoPath;
    }

    @Cacheable("photo")
    public byte[] getCocktailPhotoFromPath(String cocktailName) throws IOException {
        cocktailName = cocktailName.replaceAll("_", " ");
        cocktailName = cocktailName.replaceAll("-0001-", "'");
        cocktailName = cocktailName.replaceAll("-0010-", "`");
        cocktailName = cocktailName.replaceAll("-0011-", "’");
        cocktailName = cocktailName.replaceAll("-0100-", "*");
        cocktailName = cocktailName.replaceAll("-0101-", "#");
        InputStream in = getClass().getClassLoader().getResourceAsStream("icons/" + cocktailName + ".jpg");
        return IOUtils.toByteArray(in);
    }

}
