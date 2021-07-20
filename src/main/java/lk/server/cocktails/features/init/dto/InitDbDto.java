package lk.server.cocktails.features.init.dto;

import com.google.gson.annotations.Expose;
import lk.server.cocktails.database.ingredient.entities.Ingredient;
import lk.server.cocktails.database.modes.entities.GameMode;
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
    @Expose
    private List<GameMode> gameModes;

    @Expose
    private List<Ingredient> ingredients;

    @Expose
    private List<CocktailDto> cocktailsDto;
}
