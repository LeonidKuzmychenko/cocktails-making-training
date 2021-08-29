package lk.server.cocktails.ui.endpoint.cocktails.mappers;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.locale.Locale;
import lk.server.cocktails.locale.LocaleService;
import lk.server.cocktails.ui.endpoint.cocktails.dto.UiCocktailInfo;
import lk.server.cocktails.utils.CreatorPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RowMapperUiCocktailInfo {
    @Autowired
    private LocaleService localeService;

    @Autowired
    private CreatorPhotoService photoService;

    public UiCocktailInfo get(Cocktail cocktail, Locale locale) {
        String name = localeService.getStringByLocale(cocktail.getCocktailName(), locale);
        String association = localeService.getStringByLocale(cocktail.getCocktailAssociation(), locale);
        String type = localeService.getStringByLocale(cocktail.getCocktailType(), locale);
        String method = localeService.getStringByLocale(cocktail.getCocktailMethod(), locale);
        String note = localeService.getStringByLocale(cocktail.getCocktailNote(), locale);
        String garnish = localeService.getStringByLocale(cocktail.getCocktailGarnish(), locale);
        String photo = photoService.getPathFromCocktail(cocktail);

        UiCocktailInfo uiCocktail = new UiCocktailInfo();
        uiCocktail.setName(name);
        uiCocktail.setAssociation(association);
        uiCocktail.setType(type);
        uiCocktail.setMethod(method);
        uiCocktail.setNote(note);
        uiCocktail.setGarnish(garnish);
        uiCocktail.setPhoto(photo);
        return uiCocktail;
    }
}