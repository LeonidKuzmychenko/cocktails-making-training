package lk.server.cocktails.feachers.mix.service;

import lk.server.cocktails.feachers.cocktail.entities.Cocktail;
import lk.server.cocktails.feachers.cocktail.repositories.CocktailRepository;
import lk.server.cocktails.feachers.ingredient.entities.Ingredient;
import lk.server.cocktails.feachers.ingredient.repositories.IngredientRepository;
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

//    public Cocktail addIngredient(Long idCocktail, Long idIngredient, IngredientVolumeType volumeType, String volume) {
//        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(idCocktail);
//        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(idIngredient);
//
//        Cocktail cocktail = cocktailOptional.get();
//        Ingredient ingredient = ingredientOptional.get();
//
//        MixIngredient mixIngredient = new MixIngredient(ingredient, volumeType, volume);
//        cocktail.addIngredient(mixIngredient);
////        ingredient.addCocktail(cocktail);
//
//        cocktailRepository.save(cocktail);
//        return cocktail;
//    }
}
