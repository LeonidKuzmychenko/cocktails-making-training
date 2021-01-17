package my.project.cocktails.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/status")
//produces (content-type) то что получаем
//consumes то что отпраляем
public class CheckController {

    @GetMapping(value = "/status")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("server is starting", HttpStatus.OK);
    }

}
