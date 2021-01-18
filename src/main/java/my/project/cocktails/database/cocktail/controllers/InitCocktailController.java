package my.project.cocktails.database.cocktail.controllers;

import com.google.gson.Gson;
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
import org.springframework.beans.factory.annotation.Qualifier;
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
    public ResponseEntity<String> findAll() throws InterruptedException {

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Бейлис"),
                new IngredientName(Locale.EN, "Beilys")
        ))));

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Калуа"),
                new IngredientName(Locale.EN, "Khalua")
        ))));

        ingredientService.save(new Ingredient(new HashSet<>(Arrays.asList(
                new IngredientName(Locale.RU, "Трипл-Сек"),
                new IngredientName(Locale.EN, "Triple-Sec")
        ))));

        cocktailService.save(new Cocktail(new HashSet<>(Arrays.asList(
                new CocktailName(Locale.RU, "Б-52"),
                new CocktailName(Locale.EN, "B-52")
        )), new HashSet<>(Arrays.asList(
                new CocktailDescription(Locale.RU, "Мой любимый шот"),
                new CocktailDescription(Locale.EN, "My favorite shot")
        ))));

//        Thread.sleep(3000);
//        mixCocktailService.addIngredient(1L,1L);
//        Thread.sleep(1000);
//        mixCocktailService.addIngredient(1L,2L);
//        Thread.sleep(1000);
//        mixCocktailService.addIngredient(1L,3L);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


}
