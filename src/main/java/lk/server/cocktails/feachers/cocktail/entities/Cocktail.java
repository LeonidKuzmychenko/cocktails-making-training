package lk.server.cocktails.feachers.cocktail.entities;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lk.server.cocktails.feachers.ingredient.entities.Ingredient;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column
    @Expose
    @NotNull
    private String image;

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

    public Cocktail(@NotNull String image, @NotNull Set<CocktailName> cocktailName, @NotNull Set<CocktailAssociation> cocktailAssociation, @NotNull Set<CocktailType> cocktailType, @NotNull Set<CocktailMethod> cocktailMethod, @NotNull Set<CocktailNote> cocktailNote, @NotNull Set<CocktailGarnish> cocktailGarnish) {
        this.image = image;
        this.cocktailName = cocktailName;
        this.cocktailAssociation = cocktailAssociation;
        this.cocktailType = cocktailType;
        this.cocktailMethod = cocktailMethod;
        this.cocktailNote = cocktailNote;
        this.cocktailGarnish = cocktailGarnish;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "cocktailId=" + cocktailId +
                ", image='" + image + '\'' +
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
