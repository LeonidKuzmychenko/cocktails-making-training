package lk.server.cocktails.features.init.dto.help;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailsTransformHelperStart {
    private Long cocktailId;
    private List<String> ingredientNames;

    public CocktailsTransformHelperFinish toCocktailsTransformHelperFinish(Map<String, Long> savedIngredients) {
        CocktailsTransformHelperFinish cocktailsTransformHelperFinish = new CocktailsTransformHelperFinish();
        cocktailsTransformHelperFinish.setCocktailId(cocktailId);
        List<Long> ingredientIds = ingredientNames.stream()
                .map(savedIngredients::get)
                .collect(Collectors.toList());
        cocktailsTransformHelperFinish.setIngredientIds(ingredientIds);
        return cocktailsTransformHelperFinish;
    }
}
