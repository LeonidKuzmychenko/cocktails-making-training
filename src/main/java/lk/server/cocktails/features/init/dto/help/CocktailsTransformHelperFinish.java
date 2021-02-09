package lk.server.cocktails.features.init.dto.help;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailsTransformHelperFinish {
    private Long cocktailId;
    private List<Long> ingredientIds;
}
