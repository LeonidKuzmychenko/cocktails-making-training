package lk.server.cocktails.ui.endpoint.cocktails.mappers;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.locale.Locale;
import lk.server.cocktails.locale.LocaleService;
import lk.server.cocktails.ui.endpoint.cocktails.dto.UiCocktail;
import lk.server.cocktails.utils.CreatorPhotoService;
import lk.utils.mapper.RowMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RowMapperUiShortCocktail {

    @Autowired
    private RowMapperService<UiCocktail> rowMapper;

    @Autowired
    private LocaleService localeService;

    @Autowired
    private CreatorPhotoService photoService;

    public UiCocktail join(Cocktail cocktail, Locale locale) {
        String name = localeService.getStringByLocale(cocktail.getCocktailName(), locale);
        String photo = photoService.getPathFromCocktail(cocktail);

        UiCocktail uiCocktail = rowMapper.join(new UiCocktail(), cocktail);
        uiCocktail.setName(name);
        uiCocktail.setPhoto(photo);
        return uiCocktail;
    }


}