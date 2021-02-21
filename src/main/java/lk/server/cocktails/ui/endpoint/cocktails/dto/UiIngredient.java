package lk.server.cocktails.ui.endpoint.cocktails.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UiIngredient {

    private String name;
    private boolean consists;

}
