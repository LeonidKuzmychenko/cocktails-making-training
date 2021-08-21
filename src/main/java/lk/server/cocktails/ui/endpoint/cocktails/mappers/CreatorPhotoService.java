package lk.server.cocktails.ui.endpoint.cocktails.mappers;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.locale.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatorPhotoService {

    @Autowired
    private LocaleService localeService;

    public String getCocktailPhotoPath(Cocktail cocktail) {
        String photoPath = "photo/" + localeService.getEnString(cocktail.getCocktailName()).trim();
        photoPath = photoPath.replaceAll(" ", "_");
        photoPath = photoPath.replaceAll("’", "*");
        return photoPath;
    }

}
