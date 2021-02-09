//package lk.server.cocktails.ui.endpoint.services;
//
//import lk.server.cocktails.customtypes.locale.ILocalization;
//import lk.server.cocktails.customtypes.locale.Locale;
//import lk.server.cocktails.db.cocktail.entities.Cocktail;
//import lk.server.cocktails.db.cocktail.services.CocktailService;
//import lk.server.cocktails.db.ingredient.entities.Ingredient;
//import lk.server.cocktails.db.ingredient.services.IngredientService;
//import lk.server.cocktails.ui.endpoint.dto.UiCocktail;
//import lk.server.cocktails.ui.endpoint.dto.UiIngredient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class UiCocktailsService {
//
//    @Autowired
//    private CocktailService cocktailService;
//
//    @Autowired
//    private IngredientService ingredientService;
//
//    private final int INGREDIENT_SIZE = 10;
//
//    public List<UiCocktail> getCocktails(Locale locale) {
//        List<UiCocktail> cocktails = new ArrayList<>();
//        cocktailService.findAll().forEach(cocktail -> {
//            String name = getLocaleString(new ArrayList<>(cocktail.getCocktailNames()), locale);
//            String image = cocktail.getImage();
//            String description = getLocaleString(new ArrayList<>(cocktail.getCocktailDescriptions()), locale);
//            List<Ingredient> ingredients = cocktail.getIngredients().stream().map(MixIngredient::getIngredient).collect(Collectors.toList());
//            List<UiIngredient> uiIngredients = ingredientsToUiVersion(ingredients, true, locale);
//            uiIngredients.addAll(getNotConsistsIngredients(cocktail, INGREDIENT_SIZE - uiIngredients.size(), locale));
//            Collections.shuffle(ingredients);
//            UiCocktail uiCocktail = new UiCocktail(name, image, description, uiIngredients);
//            cocktails.add(uiCocktail);
//        });
//        return cocktails;
//    }
//
//    private String getLocaleString(List<ILocalization> collection, Locale locale) {
//        for (ILocalization item : collection) {
//            if (item.getLocale() == locale)
//                return item.getName();
//        }
//        return null; //TODO добавить исключение и его обработчик
//    }
//
//    private List<UiIngredient> ingredientsToUiVersion(Collection<Ingredient> ingredients, boolean consists, Locale locale) {
//        return ingredients.stream().map(item -> {
//            UiIngredient uiIngredient = new UiIngredient();
//            uiIngredient.setConsists(consists);
//            String name = getLocaleString(new ArrayList<>(item.getIngredientNames()), locale);
//            uiIngredient.setName(name);
//            return uiIngredient;
//        }).collect(Collectors.toList());
//    }
//
//    private List<UiIngredient> getNotConsistsIngredients(Cocktail cocktail, int limit, Locale locale) {
//        List<Long> exclude = cocktail.getIngredients().stream().map(MixIngredient::getIngredient).map(Ingredient::getIngredientId).collect(Collectors.toList());
//        List<Ingredient> ingredients = ingredientService.findRandomIngredientsWithExcludeAndLimitsIds(exclude, limit);
//        return ingredientsToUiVersion(ingredients, false, locale);
//    }
//}
