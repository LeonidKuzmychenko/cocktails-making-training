package my.project.cocktails.database.ingredient.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IngredientName")
public class IngredientName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientNameId;

    @Column
    private String locale;

    @Column
    private String name;

    public IngredientName(String locale, String name) {
        this.locale = locale;
        this.name = name;
    }
}
