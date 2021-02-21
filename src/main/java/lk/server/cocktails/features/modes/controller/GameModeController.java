package lk.server.cocktails.features.modes.controller;

import com.google.gson.Gson;
import lk.server.cocktails.features.modes.entities.GameMode;
import lk.server.cocktails.features.modes.service.GameModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/modes")
public class GameModeController {

    @Autowired
    private GameModeService service;

    @Autowired
    @Qualifier("GsonExpose")
    private Gson gson;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get() {
        List<GameMode> gameModes = service.findAll();
        return new ResponseEntity<>(gson.toJson(gameModes), HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.OK);
    }
}
