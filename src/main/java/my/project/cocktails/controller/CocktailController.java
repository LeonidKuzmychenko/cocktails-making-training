package my.project.cocktails.controller;

import com.google.gson.Gson;
import my.project.cocktails.entities.Cocktail;
import my.project.cocktails.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cocktails")
public class CocktailController {

    @Autowired
    private CocktailService cocktailService;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAll() {
        List<Cocktail> cocktails = cocktailService.findAll();
        return new ResponseEntity<>(new Gson().toJson(cocktails), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@Valid @RequestBody Cocktail cocktail) {
        Cocktail savedCocktail = cocktailService.save(cocktail);
        return new ResponseEntity<>(new Gson().toJson(savedCocktail), HttpStatus.OK);
    }

}
