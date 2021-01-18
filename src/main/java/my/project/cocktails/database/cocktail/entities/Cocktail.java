package my.project.cocktails.database.cocktail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.project.cocktails.database.ingredient.entities.Ingredient;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cocktail")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktailId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CocktailName> cocktailNames;

    @ManyToMany
    @JoinTable(
            name = "cocktails_ingredient_join",
            joinColumns = @JoinColumn(name = "cocktail_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public Cocktail(List<CocktailName> cocktailNames) {
        this.cocktailNames = cocktailNames;
    }
}
