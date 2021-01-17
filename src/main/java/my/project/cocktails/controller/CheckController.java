package my.project.cocktails.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RestController
//@RequestMapping(value = "/status")
//produces (content-type) то что отправляем
//consumes то что получаем
public class CheckController {

    @GetMapping(value = "/status")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("server is starting", HttpStatus.OK);
    }

    @GetMapping(value = "/env", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> env() {
        Map<String, String> env = System.getenv();
        String json = new Gson().toJson(env);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping(value = "/envDataBase", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> envDataBase() {
        Map<String, String> env = System.getenv();
        Map<String, String> database = new HashMap<>();
        env.forEach((key, value) -> {
            if (key.startsWith("DATABASE")) {
                database.put(key, value);
            }
        });
        String json = new Gson().toJson(database);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

}
