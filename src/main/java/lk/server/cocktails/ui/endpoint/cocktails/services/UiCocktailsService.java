package lk.server.cocktails.ui.endpoint.cocktails.services;

import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.customtypes.locale.LocaleService;
import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.cocktail.services.CocktailService;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.ingredient.services.IngredientService;
import lk.server.cocktails.ui.endpoint.cocktails.dto.UiCocktail;
import lk.server.cocktails.ui.endpoint.cocktails.dto.UiIngredient;
import lk.server.cocktails.utils.MyStreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UiCocktailsService {

    @Autowired
    private CocktailService cocktailService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private LocaleService localeService;

    public List<UiCocktail> getCocktails(Locale locale, int cSize, int iSize) {
        return cocktailService.findRandomLimitCocktails(cSize).stream()
                .map(cocktail -> cocktailToUiCocktail(cocktail, locale, iSize))
                .collect(Collectors.toList());
    }

    public UiCocktail getCocktail(Locale locale, String exclude, int iSize) {
        List<Long> list = Arrays.stream(exclude.trim().split(","))
                .map(MyStreamUtils.wrap(Long::valueOf))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Cocktail cocktail = cocktailService.findRandomCocktail(list);
        return cocktailToUiCocktail(cocktail, locale, iSize);
    }

    private UiCocktail cocktailToUiCocktail(Cocktail cocktail, Locale locale, int iSize) {
        String name = localeService.getStringByLocale((Set) cocktail.getCocktailName(), locale);
        String association = localeService.getStringByLocale((Set) cocktail.getCocktailAssociation(), locale);
        String type = localeService.getStringByLocale((Set) cocktail.getCocktailType(), locale);
        String method = localeService.getStringByLocale((Set) cocktail.getCocktailMethod(), locale);
        String note = localeService.getStringByLocale((Set) cocktail.getCocktailNote(), locale);
        String garnish = localeService.getStringByLocale((Set) cocktail.getCocktailGarnish(), locale);
        List<Ingredient> ingredients = new ArrayList<>(cocktail.getIngredients());
        List<UiIngredient> uiIngredients = ingredientsToUiVersion(ingredients, true, locale);
        uiIngredients.addAll(getNotConsistsIngredients(cocktail, iSize - uiIngredients.size(), locale));
        Collections.shuffle(uiIngredients);
        UiCocktail uiCocktail = new UiCocktail();
        uiCocktail.setName(name);
        uiCocktail.setAssociation(association);
        uiCocktail.setType(type);
        uiCocktail.setMethod(method);
        uiCocktail.setNote(note);
        uiCocktail.setGarnish(garnish);
        uiCocktail.setIngredients(uiIngredients);
        return uiCocktail;
    }

    private List<UiIngredient> ingredientsToUiVersion(Collection<Ingredient> ingredients, boolean consists, Locale locale) {
        return ingredients.stream().map(item -> {
            UiIngredient uiIngredient = new UiIngredient();
            uiIngredient.setConsists(consists);
            String name = localeService.getStringByLocale((Set) item.getIngredientNames(), locale);
            uiIngredient.setName(name);
            return uiIngredient;
        }).collect(Collectors.toList());
    }

    private List<UiIngredient> getNotConsistsIngredients(Cocktail cocktail, int limit, Locale locale) {
        List<Long> exclude = cocktail.getIngredients().stream().map(Ingredient::getIngredientId).collect(Collectors.toList());
        List<Ingredient> ingredients = ingredientService.findRandomIngredientsWithExcludeAndLimitsIds(exclude, limit);
        return ingredientsToUiVersion(ingredients, false, locale);
    }
}
