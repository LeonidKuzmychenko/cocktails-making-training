package my.project.cocktails.ui.endpoint.services;

import my.project.cocktails.data.ILocalization;
import my.project.cocktails.data.Locale;
import my.project.cocktails.database.cocktail.services.CocktailService;
import my.project.cocktails.database.ingredient.entities.Ingredient;
import my.project.cocktails.database.ingredient.services.IngredientService;
import my.project.cocktails.ui.endpoint.dto.UiCocktail;
import my.project.cocktails.ui.endpoint.dto.UiIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UiCocktailsService {

    @Autowired
    private CocktailService cocktailService;

    @Autowired
    private IngredientService ingredientService;

    public List<UiCocktail> getCocktails(Locale locale) {
        List<UiCocktail> cocktails = new ArrayList<>();
        cocktailService.findAll().forEach(cocktail -> {
            String name = getLocaleString(new ArrayList<>(cocktail.getCocktailNames()), locale);
            String image = cocktail.getImage();
            String description = getLocaleString(new ArrayList<>(cocktail.getCocktailDescriptions()), locale);
            List<UiIngredient> ingredients = ingredientsToUiVersion(cocktail.getIngredients(), locale);
            //TODO добавить неправильные варианты ответов из БД
            int notConsists = 10 - ingredients.size();
            for (int i = 0; i < notConsists; i++) {
                ingredients.add(new UiIngredient("var", false));
            }
            UiCocktail uiCocktail = new UiCocktail(name, image, description, ingredients);
            cocktails.add(uiCocktail);
        });
        return cocktails;
    }

    private String getLocaleString(List<ILocalization> set, Locale locale) {
        for (ILocalization item : set) {
            if (item.getLocale() == locale)
                return item.getName();
        }
        return null;
    }

    private List<UiIngredient> ingredientsToUiVersion(Set<Ingredient> ingredientSet, Locale locale) {
        return ingredientSet.stream().map(item -> {
            UiIngredient uiIngredient = new UiIngredient();
            uiIngredient.setConsists(true);
            String name = getLocaleString(new ArrayList<>(item.getIngredientNames()), locale);
            uiIngredient.setName(name);
            return uiIngredient;
        }).collect(Collectors.toList());
    }

}
