package my.project.cocktails.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CocktailName")
public class CocktailName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktailNameId;

    @Column
    private String locale;

    @Column
    private String name;

    public CocktailName(String locale, String name) {
        this.locale = locale;
        this.name = name;
    }
}
