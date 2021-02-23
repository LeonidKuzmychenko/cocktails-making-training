package lk.server.cocktails.features.init.dto;

import com.google.gson.annotations.Expose;
import lk.server.cocktails.features.cocktail.entities.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CocktailDto {

    @Expose
    private Long cocktailId;

    @Expose
    private Set<CocktailName> cocktailName;

    @Expose
    private Set<CocktailAssociation> cocktailAssociation;

    @Expose
    private Set<CocktailType> cocktailType;

    @Expose
    private Set<CocktailMethod> cocktailMethod;

    @Expose
    private Set<CocktailNote> cocktailNote;

    @Expose
    private Set<CocktailGarnish> cocktailGarnish;

    @Expose
    private Set<String> ingredients = new HashSet<>();

    public Cocktail toCocktail() {
        Cocktail cocktail = new Cocktail();
        cocktail.setCocktailName(getCocktailName());
        cocktail.setCocktailType(getCocktailType());
        cocktail.setCocktailAssociation(getCocktailAssociation());
        cocktail.setCocktailMethod(getCocktailMethod());
        cocktail.setCocktailNote(getCocktailNote());
        cocktail.setCocktailGarnish(getCocktailGarnish());
        return cocktail;
    }
}
