package my.project.cocktails.database.cocktail.entities;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CocktailName")
public class CocktailName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long cocktailNameId;

    @Column
    @Expose
    private String locale;

    @Column
    @Expose
    private String name;

    public CocktailName(String locale, String name) {
        this.locale = locale;
        this.name = name;
    }
}
