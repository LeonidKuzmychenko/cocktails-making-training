package lk.server.cocktails.database.cocktail.entities;

import com.google.gson.annotations.Expose;
import lk.server.cocktails.database.ingredient.entities.Ingredient;
import lk.utils.mapper.annotation.RowMapper;
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
@Table(name = "cocktail")
public class Cocktail {

    @Id
    @Column(name = "cocktail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    @RowMapper(name = "LongCocktailId")
    private Long cocktailId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    @RowMapper(name = "SetCocktailName")
    private Set<CocktailName> cocktailName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    @RowMapper(name = "SetCocktailAssociation")
    private Set<CocktailAssociation> cocktailAssociation;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    @RowMapper(name = "SetCocktailType")
    private Set<CocktailType> cocktailType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    @RowMapper(name = "SetCocktailMethod")
    private Set<CocktailMethod> cocktailMethod;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    @RowMapper(name = "SetCocktailNote")
    private Set<CocktailNote> cocktailNote;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    @RowMapper(name = "SetCocktailGarnish")
    private Set<CocktailGarnish> cocktailGarnish;

    @ManyToMany
    @JoinTable(
            name = "cocktails_ingredient_join",
            joinColumns = @JoinColumn(name = "cocktail_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    @Expose
    @NotNull
    @RowMapper(name = "SetIngredient")
    private Set<Ingredient> ingredients = new HashSet<>();

    public void addIngredient(@NotNull Ingredient ingredient) {
        ingredients.add(ingredient);
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
