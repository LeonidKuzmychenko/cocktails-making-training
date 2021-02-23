package lk.server.cocktails.features.init.services;

import com.google.gson.reflect.TypeToken;
import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.init.dto.CocktailDto;
import lk.server.cocktails.features.init.dto.CocktailsMixDto;
import lk.server.cocktails.features.modes.entities.GameMode;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InitServiceAbstract {

    protected List<CocktailsMixDto> merge(List<Cocktail> cocktails, List<CocktailDto> cocktailsDto, Map<String, Long> savedIngredients) {
        List<CocktailsMixDto> cocktailsTransform = new ArrayList<>();
        for (int i = 0; i < cocktails.size(); i++) {
            Cocktail cocktail = cocktails.get(i);
            CocktailDto cocktailDto = cocktailsDto.get(i);
            CocktailsMixDto cocktailTransform = new CocktailsMixDto();
            cocktailTransform.setCocktailId(cocktail.getCocktailId());
            List<Long> ids = cocktailDto.getIngredients().stream()
                    .map(savedIngredients::get)
                    .collect(Collectors.toList());
            cocktailTransform.setIngredientIds(ids);
        }
        return cocktailsTransform;
    }

    protected Type getGameModeTypeList() {
        return new TypeToken<List<GameMode>>() {
        }.getType();
    }

    protected Type getIngredientsTypeList() {
        return new TypeToken<List<Ingredient>>() {
        }.getType();
    }

    protected Type getCocktailTypeList() {
        return new TypeToken<List<CocktailDto>>() {
        }.getType();
    }

}
