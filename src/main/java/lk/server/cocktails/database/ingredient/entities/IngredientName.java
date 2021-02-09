package lk.server.cocktails.database.ingredient.entities;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lk.server.cocktails.customtypes.locale.ILocalization;
import lk.server.cocktails.customtypes.locale.Locale;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IngredientName")
public class IngredientName implements ILocalization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long ingredientNameId;

    @Column
    @Expose
    private Locale locale;

    @Column
    @Expose
    private String name;

    public IngredientName(Locale locale, String name) {
        this.locale = locale;
        this.name = name;
    }
}
