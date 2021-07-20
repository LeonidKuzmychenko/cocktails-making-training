package lk.server.cocktails.features.init.services.parent;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.database.cocktail.services.CocktailService;
import lk.server.cocktails.database.ingredient.entities.Ingredient;
import lk.server.cocktails.database.ingredient.services.IngredientService;
import lk.server.cocktails.database.modes.entities.GameMode;
import lk.server.cocktails.database.modes.service.GameModeService;
import lk.server.cocktails.features.init.dto.CocktailDto;
import lk.server.cocktails.features.init.dto.CocktailMixDto;
import lk.server.cocktails.features.init.mappers.RowMapperCocktail;
import lk.server.cocktails.features.init.mappers.RowMapperCocktailMixDto;
import lk.server.cocktails.features.mix.services.MixCocktailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class InitDbService {

    @Autowired
    protected GameModeService gameModeService;

    @Autowired
    protected IngredientService ingredientService;

    @Autowired
    protected CocktailService cocktailService;

    @Autowired
    protected MixCocktailService mixCocktailService;

    @Autowired
    protected RowMapperCocktail rowMapperCocktail;

    @Autowired
    protected RowMapperCocktailMixDto rowMapperCocktailMixDto;

    protected List<GameMode> initModes(List<GameMode> gameModes) {
        return gameModeService.saveAll(gameModes);
    }

    protected List<Ingredient> initIngredients(List<Ingredient> ingredients) {
        return ingredientService.saveAll(ingredients);
    }

    protected List<Cocktail> initCocktails(List<CocktailDto> cocktailsDto) {
        List<Cocktail> cocktails = cocktailsDto.stream().map(it -> rowMapperCocktail.join(it)).collect(Collectors.toList());
        return cocktailService.saveAll(cocktails);
    }

    protected List<Cocktail> mixCocktails(List<Cocktail> cocktails, List<CocktailDto> cocktailsDto, List<Ingredient> ingredients) {
        List<CocktailMixDto> cocktailsMixDto = rowMapperCocktailMixDto.join(cocktails, cocktailsDto, ingredients);
        return mixCocktailService.addIngredientsByCocktailMixDto(cocktailsMixDto);
    }

}
