package lk.server.cocktails.feachers.ingredient.services;

import lk.server.cocktails.feachers.ingredient.entities.Ingredient;
import lk.server.cocktails.feachers.ingredient.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    public List<Ingredient> findAll() {
        return repository.findAll();
    }

    public Ingredient save(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public List<Ingredient> findRandomIngredientsWithExcludeAndLimitsIds(List<Long> exclude, int limit) {
        return repository.findRandomIngredientsWithExcludeAndLimitsIds(exclude, limit);
    }
}
