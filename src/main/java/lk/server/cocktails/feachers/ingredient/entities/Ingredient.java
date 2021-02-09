package lk.server.cocktails.feachers.ingredient.entities;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lk.server.cocktails.feachers.cocktail.entities.Cocktail;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.HashSet;
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
    @NotNull
    private Set<IngredientName> ingredientNames;

    @ManyToMany(mappedBy = "ingredients")
    @NotNull
    private Set<Cocktail> cocktails = new HashSet<>();

    public Ingredient(@NotNull Set<IngredientName> ingredientNames) {
        this.ingredientNames = ingredientNames;
    }

    public void addCocktail(Cocktail cocktail) {
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
