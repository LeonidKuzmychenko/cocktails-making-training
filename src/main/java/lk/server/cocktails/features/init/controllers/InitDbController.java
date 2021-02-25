package lk.server.cocktails.features.init.controllers;

import lk.server.cocktails.features.init.dto.InitDbDto;
import lk.server.cocktails.features.init.services.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cocktails")
public class InitDbController {

    @Autowired
    private InitDbService initDbService;

    @PostMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> init(@RequestBody InitDbDto initDbDto) {
        initDbService.init(initDbDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}