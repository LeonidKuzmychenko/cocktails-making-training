package my.project.cocktails.database.ingredient.entities;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IngredientName")
public class IngredientName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long ingredientNameId;

    @Column
    @Expose
    private String locale;

    @Column
    @Expose
    private String name;

    public IngredientName(String locale, String name) {
        this.locale = locale;
        this.name = name;
    }
}
