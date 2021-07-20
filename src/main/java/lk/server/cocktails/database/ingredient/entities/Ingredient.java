package lk.server.cocktails.database.ingredient.entities;

import com.google.gson.annotations.Expose;
import lk.server.cocktails.database.cocktail.entities.Cocktail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @Column(name = "ingredient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long ingredientId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    private Set<IngredientName> ingredientNames;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Cocktail> cocktails = new HashSet<>();

    public Ingredient(@NotNull Set<IngredientName> ingredientNames) {
        this.ingredientNames = ingredientNames;
    }

    public void addCocktail(Cocktail cocktail) {
        if (cocktails == null) {
            cocktails = new HashSet<>();
        }
        cocktails.add(cocktail);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", ingredientNames=" + ingredientNames +
                '}';
    }

}
