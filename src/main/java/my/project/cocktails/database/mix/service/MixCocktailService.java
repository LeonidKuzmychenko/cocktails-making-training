package my.project.cocktails.database.mix.service;

import my.project.cocktails.database.cocktail.entities.Cocktail;
import my.project.cocktails.database.cocktail.repositories.CocktailRepository;
import my.project.cocktails.database.ingredient.entities.Ingredient;
import my.project.cocktails.database.ingredient.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MixCocktailService {

    @Autowired
    private CocktailRepository cocktailRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public Cocktail addIngredient(Long idCocktail, Long idIngredient) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(idCocktail);
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(idIngredient);

        Cocktail cocktail = cocktailOptional.get();
        Ingredient ingredient = ingredientOptional.get();

        cocktail.addIngredient(ingredient);
        ingredient.addCocktail(cocktail);

        cocktailRepository.save(cocktail);
        return cocktail;
    }
}
