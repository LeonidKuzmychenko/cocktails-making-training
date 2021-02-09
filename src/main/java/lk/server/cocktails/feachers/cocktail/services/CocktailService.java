package lk.server.cocktails.feachers.cocktail.services;

import lk.server.cocktails.feachers.cocktail.entities.Cocktail;
import lk.server.cocktails.feachers.cocktail.repositories.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocktailService {

    @Autowired
    private CocktailRepository repository;

    public List<Cocktail> findAll() {
        return repository.findAll();
    }

    public Cocktail save(Cocktail cocktail) {
        return repository.save(cocktail);
    }

}
