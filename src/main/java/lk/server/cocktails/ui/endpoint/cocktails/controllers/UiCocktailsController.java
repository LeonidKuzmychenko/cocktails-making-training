package lk.server.cocktails.ui.endpoint.cocktails.controllers;

import com.google.gson.Gson;
import lk.server.cocktails.locale.Locale;
import lk.server.cocktails.ui.endpoint.cocktails.dto.UiCocktail;
import lk.server.cocktails.ui.endpoint.cocktails.dto.UiCocktailInfo;
import lk.server.cocktails.ui.endpoint.cocktails.services.UiCocktailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> getAllCocktails(@RequestHeader("locale") Locale locale,
                                                  @RequestParam("cSize") Integer cSize,
                                                  @RequestParam("iSize") Integer iSize) {
        List<UiCocktail> cocktails = uiCocktailsService.getCocktails(locale, cSize, iSize);
        return new ResponseEntity<>(gson.toJson(cocktails), HttpStatus.OK);
    }

    @GetMapping(value = "/all/short", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllShortCocktails(@RequestHeader("locale") Locale locale) {
        List<UiCocktail> cocktails = uiCocktailsService.getShortCocktails(locale);
        return new ResponseEntity<>(gson.toJson(cocktails), HttpStatus.OK);
    }

    @GetMapping(value = "/one", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getModeOne(@RequestHeader("locale") Locale locale,
                                             @RequestParam("exclude") String exclude,
                                             @RequestParam("iSize") Integer iSize) {
        UiCocktail cocktail = uiCocktailsService.getCocktail(locale, exclude, iSize);
        if (cocktail == null) {
            return new ResponseEntity<>(null, null, 215);
        }
        return new ResponseEntity<>(gson.toJson(cocktail), HttpStatus.OK);
    }

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UiCocktailInfo> getById(@RequestParam("id") Long id, @RequestHeader("locale") Locale locale) {
        return uiCocktailsService.getUiCocktailInfo(id, locale).map(cocktail -> new ResponseEntity<>(cocktail, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
