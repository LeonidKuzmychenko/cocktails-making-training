package lk.server.cocktails.features.init.dto;

import com.google.gson.annotations.Expose;
import lk.server.cocktails.features.cocktail.entities.*;
import lk.utils.mapper.annotation.RowMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CocktailDto {

    @Expose
    @RowMapper(name = "SetCocktailName")
    private Set<CocktailName> cocktailName;

    @Expose
    @RowMapper(name = "SetCocktailAssociation")
    private Set<CocktailAssociation> cocktailAssociation;

    @Expose
    @RowMapper(name = "SetCocktailType")
    private Set<CocktailType> cocktailType;

    @Expose
    @RowMapper(name = "SetCocktailMethod")
    private Set<CocktailMethod> cocktailMethod;

    @Expose
    @RowMapper(name = "SetCocktailNote")
    private Set<CocktailNote> cocktailNote;

    @Expose
    @RowMapper(name = "SetCocktailGarnish")
    private Set<CocktailGarnish> cocktailGarnish;

    @Expose
    @RowMapper(name = "SetStringIngredients")
    private Set<String> ingredients = new HashSet<>();

}
