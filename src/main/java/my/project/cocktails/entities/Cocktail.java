package my.project.cocktails.entities;

import javax.persistence.*;

@Entity
@Table(name = "Cocktail")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktailId;

    @Column
    private String cocktailName;

    public Cocktail() {
    }

    public Cocktail(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public Cocktail(Long cocktailId, String cocktailName) {
        this.cocktailId = cocktailId;
        this.cocktailName = cocktailName;
    }

    public Long getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(Long cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "cocktailId=" + cocktailId +
                ", cocktailName='" + cocktailName + '\'' +
                '}';
    }
}
