package lk.server.cocktails.features.cocktail.entities;

import com.google.gson.annotations.Expose;
import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.init.dto.CocktailFileStructure;
import lk.server.cocktails.features.init.dto.IngredientFileStructure;
import lk.server.cocktails.features.init.dto.help.CocktailsTransformHelperStart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Cocktail")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long cocktailId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    private Set<CocktailName> cocktailName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    private Set<CocktailAssociation> cocktailAssociation;

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

    public Cocktail() {

    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "cocktailId=" + cocktailId +
                ", cocktailNames=" + cocktailName +
                ", cocktailAssociations=" + cocktailAssociation +
                ", cocktailType=" + cocktailType +
                ", cocktailMethod=" + cocktailMethod +
                ", cocktailNote=" + cocktailNote +
                ", cocktailGarnish=" + cocktailGarnish +
                ", ingredients=" + ingredients +
                '}';
    }
}
