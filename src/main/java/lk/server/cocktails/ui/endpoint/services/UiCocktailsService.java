package lk.server.cocktails.ui.endpoint.services;

import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.customtypes.locale.LocaleService;
import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.cocktail.services.CocktailService;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.ingredient.services.IngredientService;
import lk.server.cocktails.ui.endpoint.dto.UiCocktail;
import lk.server.cocktails.ui.endpoint.dto.UiIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
        List<UiCocktail> cocktails = new ArrayList<>();
        cocktailService.findRandomLimitCocktails(cSize).forEach(cocktail -> {
            String name = localeService.getStringByLocale(new ArrayList<>(cocktail.getCocktailName()), locale);
            String association = localeService.getStringByLocale(new ArrayList<>(cocktail.getCocktailAssociation()), locale);
            String type = localeService.getStringByLocale(new ArrayList<>(cocktail.getCocktailType()), locale);
            String method = localeService.getStringByLocale(new ArrayList<>(cocktail.getCocktailMethod()), locale);
            String note = localeService.getStringByLocale(new ArrayList<>(cocktail.getCocktailNote()), locale);
            String garnish = localeService.getStringByLocale(new ArrayList<>(cocktail.getCocktailGarnish()), locale);
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
            cocktails.add(uiCocktail);
        });
        return cocktails;
    }

    private List<UiIngredient> ingredientsToUiVersion(Collection<Ingredient> ingredients, boolean consists, Locale locale) {
        return ingredients.stream().map(item -> {
            UiIngredient uiIngredient = new UiIngredient();
            uiIngredient.setConsists(consists);
            String name = localeService.getStringByLocale(new ArrayList<>(item.getIngredientNames()), locale);
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
