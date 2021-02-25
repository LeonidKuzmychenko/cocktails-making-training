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
        List<GameMode> gameModes = initModes(dtos.getGameModes());
        List<Ingredient> ingredients = initIngredients(dtos.getIngredients());
        List<Cocktail> cocktails = initCocktails(dtos.getCocktailsDto());
        List<Cocktail> mixedCocktails = mixCocktails(cocktails, dtos.getCocktailsDto(), ingredients);
    }

}
