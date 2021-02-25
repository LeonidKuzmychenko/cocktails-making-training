package lk.server.cocktails.features.init.dto;

import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.modes.entities.GameMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InitDbDto {
    private List<GameMode> gameModes;
    private List<Ingredient> ingredients;
    private List<CocktailDto> cocktailsDto;
}
