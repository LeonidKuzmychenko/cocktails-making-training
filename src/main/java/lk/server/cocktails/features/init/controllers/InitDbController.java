package lk.server.cocktails.features.init.controllers;

import com.google.gson.Gson;
import lk.server.cocktails.features.init.dto.InitDbDto;
import lk.server.cocktails.features.init.services.InitDbByFileService;
import lk.server.cocktails.features.init.services.InitDbByWebService;
import lk.server.cocktails.features.init.services.ReadDbByWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/db")
public class InitDbController {

    @Autowired
    private InitDbByWebService initDbByWebService;

    @Autowired
    private InitDbByFileService initDbByFileService;

    @Autowired
    private ReadDbByWebService readDbByWebService;

    @Autowired
    @Qualifier("GsonExpose")
    private Gson gson;

    @PostMapping(value = "/initByWeb", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> initByWeb(@RequestBody InitDbDto initDbDto) {
        System.out.println("initByWeb");
        System.out.println("InitDbDto = " + initDbDto);
        initDbByWebService.init(initDbDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/initByFile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> initByFile() {
        try {
            System.out.println("initByFile");
            initDbByFileService.init();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/readByFile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> readByFile() {
        try {
            System.out.println("readByFile");
            InitDbDto initDbDto = initDbByFileService.read();
            System.out.println("InitDbDto = " + initDbDto);
            return new ResponseEntity<>(gson.toJson(initDbDto), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/readByWeb", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> readByWeb() {
        System.out.println("readByWeb");
        InitDbDto initDbDto = readDbByWebService.read();
        System.out.println("InitDbDto = " + initDbDto);
        return new ResponseEntity<>(gson.toJson(initDbDto), HttpStatus.OK);
    }

}