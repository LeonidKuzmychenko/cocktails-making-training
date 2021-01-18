package my.project.cocktails.ui.endpoint.controllers;

import com.google.gson.Gson;
import my.project.cocktails.data.Locale;
import my.project.cocktails.ui.endpoint.dto.UiCocktail;
import my.project.cocktails.ui.endpoint.services.UiCocktailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ui")
public class UiCocktailsController {

    @Autowired
    private UiCocktailsService uiCocktailsService;

    @Autowired
    @Qualifier("Gson")
    private Gson gson;

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(@RequestParam("locale") Locale locale) {
        List<UiCocktail> cocktails = uiCocktailsService.getCocktails(locale);
        return new ResponseEntity<>(gson.toJson(cocktails), HttpStatus.OK);
    }
}
