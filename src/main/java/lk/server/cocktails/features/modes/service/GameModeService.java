package lk.server.cocktails.features.modes.service;

import lk.server.cocktails.features.modes.entities.GameMode;
import lk.server.cocktails.features.modes.repositories.GameModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameModeService {

    @Autowired
    private GameModeRepository repository;

    public GameMode save(GameMode gameMode){
        return repository.save(gameMode);
    }

    public List<GameMode> saveAll(List<GameMode> gameModes){
        return repository.saveAll(gameModes);
    }

    public List<GameMode> findAll(){
        return repository.findAll();
    }

}
