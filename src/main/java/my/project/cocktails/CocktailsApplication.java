package my.project.cocktails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
        "my.project.cocktails.database.cocktail.entities",
        "my.project.cocktails.database.ingredient.entities"
})
@EnableJpaRepositories(basePackages = {
        "my.project.cocktails.database.cocktail.repositories",
        "my.project.cocktails.database.ingredient.repositories"
})
public class CocktailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CocktailsApplication.class, args);
    }

}
