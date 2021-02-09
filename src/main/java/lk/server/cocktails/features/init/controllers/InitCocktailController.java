package lk.server.cocktails.features.init.controllers;

import lk.server.cocktails.features.init.services.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/cocktails")
public class InitCocktailController {

    @Autowired
    private InitService initService;

    @GetMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> init() throws IOException {
        initService.init();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}