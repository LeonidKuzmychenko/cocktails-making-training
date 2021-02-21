package lk.server.cocktails.ui.endpoint.cocktails.controllers;

import com.google.gson.Gson;
import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.ui.endpoint.cocktails.dto.UiCocktail;
import lk.server.cocktails.ui.endpoint.cocktails.services.UiCocktailsService;
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
@RequestMapping(value = "/ui/cocktail")
public class UiCocktailsController {

    @Autowired
    private UiCocktailsService uiCocktailsService;

    @Autowired
    @Qualifier("Gson")
    private Gson gson;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getModeAll(@RequestParam("locale") Locale locale,
                                             @RequestParam("cSize") Integer cSize,
                                             @RequestParam("iSize") Integer iSize) {
        List<UiCocktail> cocktails = uiCocktailsService.getCocktails(locale, cSize, iSize);
        return new ResponseEntity<>(gson.toJson(cocktails), HttpStatus.OK);
    }

    @GetMapping(value = "/one", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getModeOne(@RequestParam("locale") Locale locale,
                                             @RequestParam("exclude") String exclude,
                                             @RequestParam("iSize") Integer iSize) {
        UiCocktail cocktail = uiCocktailsService.getCocktail(locale, exclude, iSize);
        return new ResponseEntity<>(gson.toJson(cocktail), HttpStatus.OK);
    }
}
