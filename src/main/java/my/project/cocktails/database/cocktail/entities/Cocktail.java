package my.project.cocktails.database.cocktail.entities;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.project.cocktails.database.ingredient.entities.Ingredient;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cocktail")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long cocktailId;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    private Set<CocktailName> cocktailNames;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    private Set<CocktailDescription> cocktailDescriptions;

    @ManyToMany
    @JoinTable(
            name = "cocktails_ingredient_join",
            joinColumns = @JoinColumn(name = "cocktail_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    @Expose
    private Set<Ingredient> ingredients;

    public Cocktail(@NotNull Set<CocktailName> cocktailNames, @NotNull Set<CocktailDescription> cocktailDescriptions) {
        this.cocktailNames = cocktailNames;
        this.cocktailDescriptions = cocktailDescriptions;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}
