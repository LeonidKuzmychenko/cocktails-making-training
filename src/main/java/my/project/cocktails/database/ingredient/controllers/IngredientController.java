package my.project.cocktails.database.ingredient.controllers;

import com.google.gson.Gson;
import my.project.cocktails.database.ingredient.entities.Ingredient;
import my.project.cocktails.database.ingredient.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cocktailsIngredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAll() {
        List<Ingredient> cocktails = ingredientService.findAll();
        return new ResponseEntity<>(new Gson().toJson(cocktails), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@Valid @RequestBody Ingredient ingredient) {
        System.out.println("CocktailIngredientController save");
        Ingredient savedIngredient = ingredientService.save(ingredient);
        return new ResponseEntity<>(new Gson().toJson(savedIngredient), HttpStatus.OK);
    }

}
