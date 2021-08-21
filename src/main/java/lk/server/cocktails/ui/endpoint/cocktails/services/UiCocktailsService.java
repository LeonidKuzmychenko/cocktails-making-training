package lk.server.cocktails.ui.endpoint.cocktails.services;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.database.cocktail.services.CocktailService;
import lk.server.cocktails.locale.Locale;
import lk.server.cocktails.ui.endpoint.cocktails.dto.UiCocktail;
import lk.server.cocktails.ui.endpoint.cocktails.mappers.RowMapperUiCocktail;
import lk.server.cocktails.ui.endpoint.cocktails.mappers.RowMapperUiShortCocktail;
import lk.server.cocktails.utils.MyStreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UiCocktailsService {

    @Autowired
    private CocktailService cocktailService;

    @Autowired
    private RowMapperUiCocktail rowMapperUiCocktail;

    @Autowired
    private RowMapperUiShortCocktail rowMapperUiShortCocktail;

    public List<UiCocktail> getCocktails(Locale locale, int cSize, int iSize) {
        return cocktailService.findRandomLimitCocktails(cSize).stream()
                .map(cocktail -> cocktailToUiCocktail(cocktail, locale, iSize))
                .collect(Collectors.toList());
    }

    public List<UiCocktail> getShortCocktails(Locale locale) {
        return cocktailService.findAll().stream()
                .map(cocktail -> rowMapperUiShortCocktail.join(cocktail, locale))
                .collect(Collectors.toList());
    }

    public UiCocktail getCocktail(Locale locale, String exclude, int iSize) {
        List<Long> list = Arrays.stream(exclude.trim().split(","))
                .map(MyStreamUtils.wrap(Long::valueOf))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Cocktail cocktail = cocktailService.findRandomCocktail(list);
        return (cocktail == null) ? null : cocktailToUiCocktail(cocktail, locale, iSize);
    }

    private UiCocktail cocktailToUiCocktail(Cocktail cocktail, Locale locale, int iSize) {
        return rowMapperUiCocktail.join(cocktail, locale, iSize);
    }
}
