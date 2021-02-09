package lk.server.cocktails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
        "lk.server.cocktails.database.cocktail.entities",
        "lk.server.cocktails.database.ingredient.entities"
})
@EnableJpaRepositories(basePackages = {
        "lk.server.cocktails.database.cocktail.repositories",
        "lk.server.cocktails.database.ingredient.repositories"
})
public class CocktailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CocktailsApplication.class, args);
    }

}
