package lk.server.cocktails.database.cocktail.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedCocktailsDto2 {
    private Long cocktailId;
    private String cocktailName;
    private List<Long> ingredientIds;
}
