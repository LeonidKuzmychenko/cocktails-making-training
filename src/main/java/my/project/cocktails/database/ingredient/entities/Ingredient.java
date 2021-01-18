package my.project.cocktails.database.ingredient.entities;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.project.cocktails.database.cocktail.entities.Cocktail;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long ingredientId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    private List<IngredientName> ingredientNames;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Cocktail> cocktails;

    public Ingredient(List<IngredientName> ingredientNames) {
        this.ingredientNames = ingredientNames;
    }

    public void addCocktail(Cocktail cocktail) {
        cocktails.add(cocktail);
    }

}
