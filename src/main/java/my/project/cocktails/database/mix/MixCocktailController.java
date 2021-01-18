package my.project.cocktails.database.mix;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import my.project.cocktails.database.cocktail.entities.Cocktail;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping(value = "/mix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mix(
            @RequestParam("idCocktail") Long idCocktail,
            @RequestParam("idIngredient") Long idIngredient) {
        Cocktail savedCocktail = mixCocktailService.addIngredient(idCocktail, idIngredient);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return new ResponseEntity<>(gson.toJson(savedCocktail), HttpStatus.OK);
    }

}
