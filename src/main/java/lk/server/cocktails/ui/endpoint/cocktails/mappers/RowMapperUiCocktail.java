package lk.server.cocktails.ui.endpoint.cocktails.mappers;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.database.ingredient.entities.Ingredient;
import lk.server.cocktails.database.ingredient.services.IngredientService;
import lk.server.cocktails.locale.Locale;
import lk.server.cocktails.locale.LocaleService;
import lk.server.cocktails.ui.endpoint.cocktails.dto.UiCocktail;
import lk.server.cocktails.ui.endpoint.cocktails.dto.UiIngredient;
import lk.server.cocktails.utils.CreatorPhotoService;
import lk.utils.mapper.RowMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RowMapperUiCocktail {

    @Autowired
    private RowMapperService<UiCocktail> rowMapper;

    @Autowired
    private LocaleService localeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CreatorPhotoService photoService;

    public UiCocktail join(Cocktail cocktail, Locale locale, int iSize) {
        String name = localeService.getStringByLocale(cocktail.getCocktailName(), locale);
        String association = localeService.getStringByLocale(cocktail.getCocktailAssociation(), locale);
        String type = localeService.getStringByLocale(cocktail.getCocktailType(), locale);
        String method = localeService.getStringByLocale(cocktail.getCocktailMethod(), locale);
        String note = localeService.getStringByLocale(cocktail.getCocktailNote(), locale);
        String garnish = localeService.getStringByLocale(cocktail.getCocktailGarnish(), locale);
        String photo = photoService.getPathFromCocktail(cocktail);
        List<Ingredient> ingredients = new ArrayList<>(cocktail.getIngredients());
        List<UiIngredient> uiIngredients = ingredientsToUiVersion(ingredients, true, locale);
        uiIngredients.addAll(getNotConsistsIngredients(cocktail, iSize - uiIngredients.size(), locale));
        Collections.shuffle(uiIngredients);

        UiCocktail uiCocktail = rowMapper.join(new UiCocktail(), cocktail);
        uiCocktail.setName(name);
        uiCocktail.setAssociation(association);
        uiCocktail.setType(type);
        uiCocktail.setMethod(method);
        uiCocktail.setNote(note);
        uiCocktail.setGarnish(garnish);
        uiCocktail.setIngredients(uiIngredients);
        uiCocktail.setPhoto(photo);
        return uiCocktail;
    }

    private List<UiIngredient> ingredientsToUiVersion(Collection<Ingredient> ingredients, boolean consists, Locale locale) {
        return ingredients.stream().map(item -> {
            UiIngredient uiIngredient = new UiIngredient();
            uiIngredient.setConsists(consists);
            String name = localeService.getStringByLocale(item.getIngredientNames(), locale);
            uiIngredient.setName(name);
            return uiIngredient;
        }).collect(Collectors.toList());
    }

    private List<UiIngredient> getNotConsistsIngredients(Cocktail cocktail, int limit, Locale locale) {
        List<Long> exclude = cocktail.getIngredients().stream()
                .map(Ingredient::getIngredientId)
                .collect(Collectors.toList());
        List<Ingredient> ingredients = ingredientService.findRandomIngredientsWithExcludeAndLimitsIds(exclude, limit);
        return ingredientsToUiVersion(ingredients, false, locale);
    }

}