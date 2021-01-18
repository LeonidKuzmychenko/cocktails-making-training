package my.project.cocktails.database.cocktail.entities;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.project.cocktails.data.ILocale;
import my.project.cocktails.data.ILocalization;
import my.project.cocktails.data.Locale;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CocktailDescription")
public class CocktailDescription implements ILocalization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long cocktailDescriptionId;

    @Column
    @Expose
    private Locale locale;

    @Column
    @Expose
    private String name;

    public CocktailDescription(Locale locale, String name) {
        this.locale = locale;
        this.name = name;
    }
}
