package lk.server.cocktails.feachers.init.dto.help;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedCocktailsDto {
    private Long cocktailId;
    private String cocktailName;
    private List<String> ingredientNames;
}
