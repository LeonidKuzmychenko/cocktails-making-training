package lk.server.cocktails.features.init.services;

import com.google.gson.Gson;
import lk.server.cocktails.customtypes.locale.LocaleService;
import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.cocktail.services.CocktailService;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.ingredient.services.IngredientService;
import lk.server.cocktails.features.init.dto.CocktailDto;
import lk.server.cocktails.features.init.dto.CocktailsMixDto;
import lk.server.cocktails.features.init.services.parent.InitServiceAbstract;
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
public class ParseDbService extends InitServiceAbstract {

    @Autowired
    private GameModeService gameModeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CocktailService cocktailService;


    public void getGameModes(){
        gameModeService.findAll();
    }

    public void getIngredients(){

    }

    public void getCocktails(){

    }

}
