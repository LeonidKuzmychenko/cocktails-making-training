package lk.server.cocktails.features.cocktail.services;

import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.cocktail.repositories.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public List<Cocktail> findRandomLimitCocktails(int limit) {
        return repository.findRandomLimitCocktails(limit);
    }

    public Cocktail findRandomCocktail(List<Long> exclude) {
        return repository.findRandomLimitCocktail(exclude.isEmpty() ?
                Collections.singletonList(Long.MIN_VALUE) : exclude);
    }

}
