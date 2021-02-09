package lk.server.cocktails.features.cocktail.entities;

import com.google.gson.annotations.Expose;
import lk.server.cocktails.customtypes.locale.ILocalization;
import lk.server.cocktails.customtypes.locale.Locale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CocktailGarnish")
public class CocktailGarnish implements ILocalization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long cocktailGarnishId;

    @Column
    @Expose
    @NotNull
    private Locale locale;

    @Column(length = 1024)
    @Expose
    @NotNull
    private String name;

    public CocktailGarnish(@NotNull Locale locale, @NotNull String name) {
        this.locale = locale;
        this.name = name;
    }
}
