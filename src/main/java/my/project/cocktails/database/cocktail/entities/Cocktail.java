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
    private Set<CocktailAssociation> cocktailAssociations;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    private Set<CocktailType> cocktailType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    private Set<CocktailMethod> cocktailMethod;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    private Set<CocktailNote> cocktailNote;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    private Set<CocktailGarnish> cocktailGarnish;

    @ManyToMany
    @JoinTable(
            name = "cocktails_ingredient_join",
            joinColumns = @JoinColumn(name = "cocktail_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    @Expose
    @NotNull
    private Set<Ingredient> ingredients = new HashSet<>();

    public void addIngredient(@NotNull Ingredient ingredient) {
        ingredients.add(ingredient);
    }

//    @OneToMany
//    private Set<MixIngredient> ingredients = new HashSet<>();

//    public void addIngredient(@NotNull MixIngredient mixIngredient) {
//        ingredients.add(mixIngredient);
//    }

    public Cocktail(@NotNull Set<CocktailName> cocktailNames, @NotNull Set<CocktailAssociation> cocktailAssociations, @NotNull Set<CocktailType> cocktailType, @NotNull Set<CocktailMethod> cocktailMethod, @NotNull Set<CocktailNote> cocktailNote, @NotNull Set<CocktailGarnish> cocktailGarnish, @NotNull Set<Ingredient> ingredients) {
        this.cocktailNames = cocktailNames;
        this.cocktailAssociations = cocktailAssociations;
        this.cocktailType = cocktailType;
        this.cocktailMethod = cocktailMethod;
        this.cocktailNote = cocktailNote;
        this.cocktailGarnish = cocktailGarnish;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "cocktailId=" + cocktailId +
                ", image='" + image + '\'' +
                ", cocktailNames=" + cocktailNames +
                ", cocktailAssociations=" + cocktailAssociations +
                ", cocktailType=" + cocktailType +
                ", cocktailMethod=" + cocktailMethod +
                ", cocktailNote=" + cocktailNote +
                ", cocktailGarnish=" + cocktailGarnish +
                ", ingredients=" + ingredients +
                '}';
    }
}
