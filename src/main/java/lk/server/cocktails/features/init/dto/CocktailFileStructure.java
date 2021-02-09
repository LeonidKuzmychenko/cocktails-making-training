package lk.server.cocktails.features.init.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.features.cocktail.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailFileStructure {

    @Expose
    public String nameRU;

    @Expose
    public String nameEN;

    @Expose
    public String associationRU;

    @Expose
    public String associationEN;

    @Expose
    public String typeRU;

    @Expose
    public String typeEN;

    @SerializedName("ingredients")
    @Expose
    public List<IngredientFileStructure> ingredientStructures = null;

    @Expose
    public String methodRU;

    @Expose
    public String methodEN;

    @Expose
    public String noteRU;

    @Expose
    public String noteEN;

    @Expose
    public String garnishRU;

    @Expose
    public String garnishEN;

    public Cocktail toCocktail() {
        Cocktail cocktail = new Cocktail();
        cocktail.setCocktailName(new HashSet<CocktailName>() {{
            add(new CocktailName(Locale.RU, nameRU));
            add(new CocktailName(Locale.EN, nameEN));
        }});
        cocktail.setCocktailAssociation(new HashSet<CocktailAssociation>() {{
            add(new CocktailAssociation(Locale.RU, associationRU));
            add(new CocktailAssociation(Locale.EN, associationEN));
        }});
        cocktail.setCocktailType(new HashSet<CocktailType>() {{
            add(new CocktailType(Locale.RU, typeRU));
            add(new CocktailType(Locale.EN, typeEN));
        }});
        cocktail.setCocktailMethod(new HashSet<CocktailMethod>() {{
            add(new CocktailMethod(Locale.RU, methodRU));
            add(new CocktailMethod(Locale.EN, methodEN));
        }});
        cocktail.setCocktailNote(new HashSet<CocktailNote>() {{
            add(new CocktailNote(Locale.RU, noteRU));
            add(new CocktailNote(Locale.EN, noteEN));
        }});
        cocktail.setCocktailGarnish(new HashSet<CocktailGarnish>() {{
            add(new CocktailGarnish(Locale.RU, garnishRU));
            add(new CocktailGarnish(Locale.EN, garnishEN));
        }});
        return cocktail;
    }
}
