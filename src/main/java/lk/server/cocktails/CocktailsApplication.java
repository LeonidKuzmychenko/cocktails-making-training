package lk.server.cocktails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
        "lk.server.cocktails.db.cocktail.entities",
        "lk.server.cocktails.db.ingredient.entities"
})
@EnableJpaRepositories(basePackages = {
        "lk.server.cocktails.feachers.cocktail.repositories",
        "lk.server.cocktails.feachers.ingredient.repositories"
})
public class CocktailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CocktailsApplication.class, args);
    }

}
