package lk.server.cocktails.ui.endpoint.cocktails.dto;

import lk.utils.mapper.annotation.RowMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UiCocktail {

    @RowMapper(name = "LongCocktailId")
    private Long id;
    private String name;
    private String association;
    private String type;
    private String method;
    private String note;
    private String garnish;
    private String photo;
    private List<UiIngredient> ingredients;

}
