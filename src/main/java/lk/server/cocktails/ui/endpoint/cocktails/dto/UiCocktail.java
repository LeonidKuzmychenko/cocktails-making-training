package lk.server.cocktails.ui.endpoint.cocktails.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UiCocktail {

    private String name;
    private String association;
    private String type;
    private String method;
    private String note;
    private String garnish;
    private List<UiIngredient> ingredients;

}
