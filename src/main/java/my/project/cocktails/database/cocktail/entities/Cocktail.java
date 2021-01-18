package my.project.cocktails.database.cocktail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Cocktail(List<CocktailName> cocktailNames) {
        this.cocktailNames = cocktailNames;
    }
}
