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

    @Column
    @Expose
    @NotNull
    private String image;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    private Set<CocktailName> cocktailNames;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    private Set<CocktailDescription> cocktailDescriptions;


    @ManyToMany
    @JoinTable(
            name = "cocktails_ingredient_join",
            joinColumns = @JoinColumn(name = "cocktail_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    @Expose
    @NotNull
    private Set<Ingredient> ingredients = new HashSet<>();

    public Cocktail(@NotNull String image, @NotNull Set<CocktailName> cocktailNames, @NotNull Set<CocktailDescription> cocktailDescriptions) {
        this.image = image;
        this.cocktailNames = cocktailNames;
        this.cocktailDescriptions = cocktailDescriptions;
    }

    public void addIngredient(@NotNull Ingredient ingredient) {
        System.out.println(ingredient.toString());
//        if (ingredients == null)
//            ingredients = new HashSet<>();
        ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "cocktailId=" + cocktailId +
                ", image='" + image + '\'' +
                ", cocktailNames=" + cocktailNames +
                ", cocktailDescriptions=" + cocktailDescriptions +
                '}';
    }
}
