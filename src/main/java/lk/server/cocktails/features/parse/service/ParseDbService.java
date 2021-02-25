package lk.server.cocktails.features.parse.service;

import lk.server.cocktails.features.cocktail.services.CocktailService;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.ingredient.services.IngredientService;
import lk.server.cocktails.features.init.dto.CocktailDto;
import lk.server.cocktails.features.init.dto.InitDbDto;
import lk.server.cocktails.features.init.mappers.RowMapperCocktailDto;
import lk.server.cocktails.features.modes.entities.GameMode;
import lk.server.cocktails.features.modes.service.GameModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParseDbService {

    @Autowired
    private GameModeService gameModeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CocktailService cocktailService;

    @Autowired
    private RowMapperCocktailDto rowMapperCocktailDto;

    public InitDbDto parse() {
        InitDbDto initDbDto = new InitDbDto();
        System.out.println("getGameModes");
        initDbDto.setGameModes(getGameModes());
        System.out.println("getIngredients");
        initDbDto.setIngredients(getIngredients());
        System.out.println("getCocktails");
        initDbDto.setCocktailsDto(getCocktails());
        return initDbDto;
    }

    public List<GameMode> getGameModes() {
        return gameModeService.findAll();
    }

    public List<Ingredient> getIngredients() {
        return ingredientService.findAll();
    }

    public List<CocktailDto> getCocktails() {
        return cocktailService.findAll().stream()
                .map(it -> rowMapperCocktailDto.join(it))
                .collect(Collectors.toList());
    }

}
