package lk.server.cocktails.ui.endpoint.cocktails.dto;

import lk.utils.mapper.annotation.RowMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UiCocktailsShort {

    @RowMapper(name = "LongCocktailId")
    private Long id;
    private String name;
    private String photo;

}
