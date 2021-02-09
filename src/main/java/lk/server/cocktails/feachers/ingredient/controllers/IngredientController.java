package lk.server.cocktails.feachers.ingredient.controllers;

import com.google.gson.Gson;
import lk.server.cocktails.feachers.ingredient.entities.Ingredient;
import lk.server.cocktails.feachers.ingredient.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("GsonExpose")
    private Gson gson;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAll() {
        List<Ingredient> cocktails = ingredientService.findAll();
        return new ResponseEntity<>(gson.toJson(cocktails), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@Valid @RequestBody Ingredient ingredient) {
        System.out.println("CocktailIngredientController save");
        Ingredient savedIngredient = ingredientService.save(ingredient);
        return new ResponseEntity<>(gson.toJson(savedIngredient), HttpStatus.OK);
    }

}
