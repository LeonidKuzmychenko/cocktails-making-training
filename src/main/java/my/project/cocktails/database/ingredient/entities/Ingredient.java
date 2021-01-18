package my.project.cocktails.database.ingredient.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<IngredientName> ingredientNames;

    public Ingredient(List<IngredientName> ingredientNames) {
        this.ingredientNames = ingredientNames;
    }
}
