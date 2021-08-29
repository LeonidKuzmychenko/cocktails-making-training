package lk.server.cocktails.statistic.controller;

import com.google.gson.Gson;
import lk.server.cocktails.statistic.model.StatisticEntity;
import lk.server.cocktails.statistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticService service;

    @PostMapping
    public ResponseEntity<StatisticEntity> save(@RequestBody StatisticEntity statisticEntity) {
        System.out.println(statisticEntity);
        System.out.println(new Gson().toJson(statisticEntity));
        StatisticEntity savedStatisticEntity = service.saveOne(statisticEntity);
        return new ResponseEntity<>(savedStatisticEntity, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StatisticEntity>> getAll() {
        List<StatisticEntity> allStatisticEntity = service.getAll();
        return new ResponseEntity<>(allStatisticEntity, HttpStatus.OK);
    }
}
