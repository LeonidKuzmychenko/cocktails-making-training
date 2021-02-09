package lk.server.cocktails.features.init.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientStructure {

    @SerializedName("size")
    @Expose
    public String size;
    @SerializedName("typeRU")
    @Expose
    public String typeRU;
    @SerializedName("typeEN")
    @Expose
    public String typeEN;
    @SerializedName("nameRU")
    @Expose
    public String nameRU;
    @SerializedName("nameEN")
    @Expose
    public String nameEN;

}