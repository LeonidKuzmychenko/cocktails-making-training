package my.project.cocktails.data.parsing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailFileStructure {

    @SerializedName("nameRU")
    @Expose
    public String nameRU;
    @SerializedName("nameEN")
    @Expose
    public String nameEN;
    @SerializedName("associationRU")
    @Expose
    public String associationRU;
    @SerializedName("associationEN")
    @Expose
    public String associationEN;
    @SerializedName("typeRU")
    @Expose
    public String typeRU;
    @SerializedName("typeEN")
    @Expose
    public String typeEN;
    @SerializedName("ingredients")
    @Expose
    public List<IngredientStructure> ingredientStructures = null;
    @SerializedName("methodRU")
    @Expose
    public String methodRU;
    @SerializedName("methodEN")
    @Expose
    public String methodEN;
    @SerializedName("noteRU")
    @Expose
    public String noteRU;
    @SerializedName("noteEN")
    @Expose
    public String noteEN;
    @SerializedName("garnishRU")
    @Expose
    public String garnishRU;
    @SerializedName("garnishEN")
    @Expose
    public String garnishEN;

}
