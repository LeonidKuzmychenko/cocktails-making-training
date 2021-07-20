package lk.server.cocktails.features.init.mappers;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lk.server.cocktails.features.init.dto.CocktailDto;
import lk.server.cocktails.locale.LocaleService;
import lk.utils.mapper.RowMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RowMapperCocktailDto {

    @Autowired
    private RowMapperService<CocktailDto> rowMapper;

    @Autowired
    private LocaleService localeService;

    public CocktailDto join(Cocktail cocktail) {
        Set<String> ingredients = cocktail.getIngredients().stream()
                .map(it -> localeService.getEnString(new ArrayList<>(it.getIngredientNames())))
                .collect(Collectors.toSet());
        CocktailDto cocktailDto = new CocktailDto();
        cocktailDto.setIngredients(ingredients);
        return rowMapper.join(cocktailDto, cocktail);
    }
}
