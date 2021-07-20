package lk.server.cocktails.features.init.mappers;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.database.ingredient.entities.Ingredient;
import lk.server.cocktails.features.init.dto.CocktailDto;
import lk.server.cocktails.features.init.dto.CocktailMixDto;
import lk.server.cocktails.locale.LocaleService;
import lk.utils.mapper.RowMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RowMapperCocktailMixDto {

    @Autowired
    private RowMapperService<CocktailMixDto> rowMapper;

    @Autowired
    private LocaleService localeService;

    public List<CocktailMixDto> join(List<Cocktail> cocktails, List<CocktailDto> cocktailsDto, List<Ingredient> savedIngredients) {
        List<CocktailMixDto> cocktailsMixDto = new ArrayList<>();
        for (int i = 0; i < cocktails.size(); i++) {
            Cocktail cocktail = cocktails.get(i);
            CocktailDto cocktailDto = cocktailsDto.get(i);
            CocktailMixDto cocktailMixDto = join(cocktail, cocktailDto, savedIngredients);
            cocktailsMixDto.add(cocktailMixDto);
        }
        return cocktailsMixDto;
    }

    public CocktailMixDto join(Cocktail cocktail, CocktailDto cocktailDto, List<Ingredient> setIngredients) {
        CocktailMixDto cocktailMixDto = rowMapper.join(new CocktailMixDto(), cocktail);
        List<Long> ids = cocktailDto.getIngredients().stream()
                .map(it -> getIngredientIds(setIngredients).get(it))
                .collect(Collectors.toList());
        cocktailMixDto.setIngredientIds(ids);
        return cocktailMixDto;
    }

    private Map<String, Long> getIngredientIds(List<Ingredient> setIngredients) {
        return setIngredients.stream().collect(Collectors.toMap(
                key -> localeService.getEnString((Set) key.getIngredientNames()), Ingredient::getIngredientId));
    }

}
