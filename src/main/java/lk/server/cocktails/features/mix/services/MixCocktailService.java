package lk.server.cocktails.features.mix.services;

import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.cocktail.repositories.CocktailRepository;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.ingredient.repositories.IngredientRepository;
import lk.server.cocktails.features.init.dto.CocktailsMixDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        return cocktailRepository.save(cocktail);
    }

    public List<Cocktail> addIngredientsByHelperClass(List<CocktailsMixDto> cocktails) {
        List<Cocktail> cocktailsWithIngredients = cocktails.stream()
                .map(it -> {
                    Optional<Cocktail> cocktailOptional = cocktailRepository.findById(it.getCocktailId());
                    Cocktail cocktail = cocktailOptional.get();
                    it.getIngredientIds().forEach(idIngredient -> {
                        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(idIngredient);
                        Ingredient ingredient = ingredientOptional.get();
                        cocktail.addIngredient(ingredient);
                        ingredient.addCocktail(cocktail);
                    });
                    return cocktail;
                })
                .collect(Collectors.toList());
        return cocktailRepository.saveAll(cocktailsWithIngredients);
    }

}
