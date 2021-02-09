package lk.server.cocktails.features.mix.controllers;

import com.google.gson.Gson;
import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.mix.services.MixCocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cocktails")
public class MixCocktailController {

    @Autowired
    private MixCocktailService mixCocktailService;

    @Autowired
    @Qualifier("GsonExpose")
    private Gson gson;

    @PutMapping(value = "/mix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mix(
            @RequestParam("idCocktail") Long idCocktail,
            @RequestParam("idIngredient") Long idIngredient) {
        Cocktail savedCocktail = mixCocktailService.addIngredient(idCocktail, idIngredient);
        return new ResponseEntity<>(gson.toJson(savedCocktail), HttpStatus.OK);
    }

}
