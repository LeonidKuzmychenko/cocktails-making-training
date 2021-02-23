package lk.server.cocktails.features.init.services;

import com.google.gson.Gson;
import lk.server.cocktails.customtypes.locale.LocaleService;
import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.cocktail.services.CocktailService;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.ingredient.services.IngredientService;
import lk.server.cocktails.features.init.dto.CocktailDto;
import lk.server.cocktails.features.init.dto.CocktailsMixDto;
import lk.server.cocktails.features.mix.services.MixCocktailService;
import lk.server.cocktails.features.modes.entities.GameMode;
import lk.server.cocktails.features.modes.service.GameModeService;
import lk.utils.files.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InitService extends InitServiceAbstract {

    @Autowired
    private CocktailService cocktailService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private MixCocktailService mixCocktailService;

    @Autowired
    private GameModeService gameModeService;

    @Autowired
    private FileManager fileManager;

    @Autowired
    private LocaleService localeService;

    @Autowired
    @Qualifier("Gson")
    private Gson gson;

    public void init() throws IOException {
        initModes();
        Map<String, Long> map = initIngredients();
        initCocktails(map);
    }

    private void initModes() throws IOException {
        String readGameModes = fileManager.readString("src/main/resources/db_new/modes.json");
        List<GameMode> gameModes = gson.fromJson(readGameModes, getGameModeTypeList());
        gameModeService.saveAll(gameModes);
    }

    private Map<String, Long> initIngredients() throws IOException {
        String readIngredients = fileManager.readString("src/main/resources/db_new/ingredients.json");
        List<Ingredient> ingredients = gson.fromJson(readIngredients, getIngredientsTypeList());
        List<Ingredient> savedIngredients = ingredientService.saveAll(ingredients);
        return savedIngredients.stream().collect(Collectors.toMap(
                key -> localeService.getEnString((Set) key.getIngredientNames()),
                Ingredient::getIngredientId));
    }

    private void initCocktails(Map<String, Long> ingredients) throws IOException {
        String readCocktails = fileManager.readString("src/main/resources/db_new/cocktails.json");
        List<CocktailDto> cocktailsDto = gson.fromJson(readCocktails, getCocktailTypeList());
        List<Cocktail> cocktails = cocktailsDto.stream().map(CocktailDto::toCocktail).collect(Collectors.toList());
        List<Cocktail> savedCocktails = cocktailService.saveAll(cocktails);
        List<CocktailsMixDto> cocktailsMixDto = merge(savedCocktails, cocktailsDto, ingredients);
        mixCocktailService.addIngredientsByHelperClass(cocktailsMixDto);
    }

}
