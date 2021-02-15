package lk.server.cocktails.ui.endpoint.controllers;

import com.google.gson.Gson;
import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.ui.endpoint.dto.UiCocktail;
import lk.server.cocktails.ui.endpoint.services.UiCocktailsService;
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

    @GetMapping(value = "/getMode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMode(@RequestParam("locale") Locale locale,
                                         @RequestParam("cSize") Integer cSize, @RequestParam("iSize") Integer iSize) {
        List<UiCocktail> cocktails = uiCocktailsService.getCocktails(locale, cSize, iSize);
        return new ResponseEntity<>(gson.toJson(cocktails), HttpStatus.OK);
    }

}
