package lk.server.cocktails.database.cocktail.services;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.database.cocktail.repositories.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CocktailService {

    @Autowired
    private CocktailRepository repository;

    @Cacheable("cocktails")
    public List<Cocktail> findAll() {
        return repository.findAll();
    }

    public Cocktail save(Cocktail cocktail) {
        return repository.save(cocktail);
    }

    public List<Cocktail> saveAll(List<Cocktail> cocktails) {
        return repository.saveAll(cocktails);
    }

    public List<Cocktail> findRandomLimitCocktails(int limit) {
        return repository.findRandomLimitCocktails(limit);
    }

    public Cocktail findRandomCocktail(List<Long> exclude) {
        return repository.findRandomLimitCocktail(exclude.isEmpty() ?
                Collections.singletonList(Long.MIN_VALUE) : //https://hibernate.atlassian.net/browse/HHH-8091
                exclude);
    }

}
