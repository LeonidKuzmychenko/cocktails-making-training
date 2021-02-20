package lk.server.cocktails.features.cocktail.controllers;

import com.google.gson.Gson;
import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.cocktail.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cocktails")
public class CocktailController {

    @Autowired
    private CocktailService cocktailService;

    @Autowired
    @Qualifier("GsonExpose")
    private Gson gson;


    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAll() {
        List<Cocktail> cocktails = cocktailService.findAll();
        return new ResponseEntity<>(gson.toJson(cocktails), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllSortedByIngredientSize", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAllSortedByIngredientSize() {
        List<Cocktail> cocktails = cocktailService.findAll()
                .stream()
                .sorted(Comparator.comparingInt(o -> o.getIngredients().size()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(gson.toJson(cocktails), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@Valid @RequestBody Cocktail cocktail) {
        Cocktail savedCocktail = cocktailService.save(cocktail);
        return new ResponseEntity<>(gson.toJson(savedCocktail), HttpStatus.OK);
    }

}
