package lk.server.cocktails.features.init.controllers;

import lk.server.cocktails.features.init.dto.InitDbDto;
import lk.server.cocktails.features.init.services.InitDbByFileService;
import lk.server.cocktails.features.init.services.InitDbByWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/cocktails")
public class InitDbController {

    @Autowired
    private InitDbByWebService initDbByWebService;

    @Autowired
    private InitDbByFileService initDbByFileService;

    @PostMapping(value = "/initByWeb", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> initWeb(@RequestBody InitDbDto initDbDto) {
        initDbByWebService.init(initDbDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/initByFile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> initFile() {
        try {
            initDbByFileService.init();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}