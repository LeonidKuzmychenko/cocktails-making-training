package lk.server.cocktails.features.init.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailsMixDto {

    @Expose
    private Long cocktailId;

    @Expose
    private List<Long> ingredientIds;
}
