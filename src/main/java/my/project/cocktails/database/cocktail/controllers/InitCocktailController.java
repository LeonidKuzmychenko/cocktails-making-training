package my.project.cocktails.database.cocktail.controllers;

import my.project.cocktails.data.Locale;
import my.project.cocktails.database.cocktail.entities.Cocktail;
import my.project.cocktails.database.cocktail.entities.CocktailDescription;
import my.project.cocktails.database.cocktail.entities.CocktailName;
import my.project.cocktails.database.cocktail.services.CocktailService;
import my.project.cocktails.database.ingredient.entities.Ingredient;
import my.project.cocktails.database.ingredient.entities.IngredientName;
import my.project.cocktails.database.ingredient.services.IngredientService;
import my.project.cocktails.database.mix.service.MixCocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

@RestController
@RequestMapping(value = "/cocktails")
public class InitCocktailController {

    @Autowired
    private CocktailService cocktailService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private MixCocktailService mixCocktailService;

    @GetMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> findAll() {

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Айриш Крим"),
                new IngredientName(Locale.EN, "Irish Cream")
        ))));//1

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Калуа"),
                new IngredientName(Locale.EN, "Khalua")
        ))));//2

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Трипл-Сек"),
                new IngredientName(Locale.EN, "Triple-Sec")
        ))));//3

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Пизанг Амбон"),
                new IngredientName(Locale.EN, "Pizang Ambon")
        ))));//4

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Лимонный сок"),
                new IngredientName(Locale.EN, "Lemon juice")
        ))));//5

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Текила"),
                new IngredientName(Locale.EN, "Tequila")
        ))));//6

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Самбука"),
                new IngredientName(Locale.EN, "Sambuca")
        ))));//7

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Абсент"),
                new IngredientName(Locale.EN, "Absinthe")
        ))));//8

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Гренадин"),
                new IngredientName(Locale.EN, "Grenadine")
        ))));//9

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Сухой вермут"),
                new IngredientName(Locale.EN, "Dry vermouth")
        ))));//10

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Водка"),
                new IngredientName(Locale.EN, "Vodka")
        ))));//11


        cocktailService.save(new Cocktail(
                "https://www.kahlua.com/globalassets/images/cocktails/2018/opt/kahluadrinks_b521.png",
                new HashSet<>(Arrays.asList(
                        new CocktailName(Locale.RU, "Б-52"),
                        new CocktailName(Locale.EN, "B-52")
                )),
                new HashSet<>(Arrays.asList(
                        new CocktailDescription(Locale.RU, "Мой любимый шот."),
                        new CocktailDescription(Locale.EN, "My favorite shot.")
                ))));

        cocktailService.save(new Cocktail(
                "https://royalclub.md/wp-content/uploads/2018/06/8-8.png",
                new HashSet<>(Arrays.asList(
                        new CocktailName(Locale.RU, "Зеленый Мексиканец"),
                        new CocktailName(Locale.EN, "Green Mexican")
                )),
                new HashSet<>(Arrays.asList(
                        new CocktailDescription(Locale.RU, "Более прошибной, чем Б-52"),
                        new CocktailDescription(Locale.EN, "More breakable than the B-52")
                ))));

        cocktailService.save(new Cocktail(
                "https://i.pinimg.com/originals/2a/81/2f/2a812f4d95c8d869e889f307444ba285.png",
                new HashSet<>(Arrays.asList(
                        new CocktailName(Locale.RU, "Хиросима"),
                        new CocktailName(Locale.EN, "Hiroshima")
                )),
                new HashSet<>(Arrays.asList(
                        new CocktailDescription(Locale.RU, "Еще более прошибной, чем Зеленый Мексиканец."),
                        new CocktailDescription(Locale.EN, "Even more breakable than the Green Mexican.")
                ))));

        cocktailService.save(new Cocktail(
                "https://static.winestreet.ru/off-line/cocktail/55/image_S.png",
                new HashSet<>(Arrays.asList(
                        new CocktailName(Locale.RU, "Опухоль мозга"),
                        new CocktailName(Locale.EN, "Brain tumor")
                )),
                new HashSet<>(Arrays.asList(
                        new CocktailDescription(Locale.RU, "Красивый, но вонючий. Внутри свернувшиеся сливки."),
                        new CocktailDescription(Locale.EN, "Nice but smelly. Curdled cream inside.")
                ))));

        mixCocktailService.addIngredient(1L, 1L);
        mixCocktailService.addIngredient(1L, 2L);
        mixCocktailService.addIngredient(1L, 3L);

        mixCocktailService.addIngredient(2L, 4L);
        mixCocktailService.addIngredient(2L, 5L);
        mixCocktailService.addIngredient(2L, 6L);

        mixCocktailService.addIngredient(3L, 1L);
        mixCocktailService.addIngredient(3L, 7L);
        mixCocktailService.addIngredient(3L, 8L);
        mixCocktailService.addIngredient(3L, 9L);

        mixCocktailService.addIngredient(4L, 1L);
        mixCocktailService.addIngredient(4L, 9L);
        mixCocktailService.addIngredient(4L, 10L);
        mixCocktailService.addIngredient(4L, 11L);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
