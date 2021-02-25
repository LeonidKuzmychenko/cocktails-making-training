package lk.server.cocktails.features.parse.controller;

import com.google.gson.Gson;
import lk.server.cocktails.features.init.dto.InitDbDto;
import lk.server.cocktails.features.parse.service.ParseDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cocktails")
public class ParseDbController {

    @Autowired
    private ParseDbService parseDbService;

    @Autowired
    private Gson gson;

    @GetMapping(value = "/parse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> parse() {
        InitDbDto initDbDto = parseDbService.parse();
        return new ResponseEntity<>(gson.toJson(initDbDto), HttpStatus.OK);
    }

}