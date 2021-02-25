package lk.server.cocktails.features.init.services;

import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.init.dto.InitDbDto;
import lk.server.cocktails.features.init.services.parent.InitDbService;
import lk.server.cocktails.features.modes.entities.GameMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitDbByWebService extends InitDbService {

    public void init(InitDbDto dtos) {
        System.out.println("gameModes");
        List<GameMode> gameModes = initModes(dtos.getGameModes());
        System.out.println("ingredients");
        List<Ingredient> ingredients = initIngredients(dtos.getIngredients());
        System.out.println("cocktails");
        List<Cocktail> cocktails = initCocktails(dtos.getCocktailsDto());
        System.out.println("mixedCocktails");
        List<Cocktail> mixedCocktails = mixCocktails(cocktails, dtos.getCocktailsDto(), ingredients);
    }

}
