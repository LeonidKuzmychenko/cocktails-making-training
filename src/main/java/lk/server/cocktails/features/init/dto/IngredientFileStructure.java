package lk.server.cocktails.features.init.dto;

import com.google.gson.annotations.Expose;
import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.ingredient.entities.IngredientName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientFileStructure {

    @Expose
    private String nameRU;

    @Expose
    private String nameEN;

    public Ingredient toIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientNames(new HashSet<IngredientName>() {{
            add(new IngredientName(Locale.RU, nameRU));
            add(new IngredientName(Locale.EN, nameEN));
        }});
        return ingredient;
    }
}
