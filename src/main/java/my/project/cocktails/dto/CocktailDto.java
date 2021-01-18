package my.project.cocktails.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import my.project.cocktails.entities.Cocktail;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class CocktailDto {

    @SerializedName("cocktailName")
    @Expose
    @NotNull
    @JsonProperty("cocktailName")
    private String cocktailName;

//    public CocktailDto() {
//    }
//
//    public CocktailDto(@NotNull String cocktailName) {
//        this.cocktailName = cocktailName;
//    }
//
//    public String getCocktailName() {
//        return cocktailName;
//    }
//
//    public void setCocktailName(String cocktailName) {
//        this.cocktailName = cocktailName;
//    }

    public Cocktail toCocktail(){
        return new Cocktail(cocktailName);
    }

//    @Override
//    public String toString() {
//        return "CocktailDto{" +
//                "cocktailName='" + cocktailName + '\'' +
//                '}';
//    }
}
