package lk.server.cocktails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
        "lk.server.cocktails.features.cocktail.entities",
        "lk.server.cocktails.features.ingredient.entities"
})
@EnableJpaRepositories(basePackages = {
        "lk.server.cocktails.features.cocktail.repositories",
        "lk.server.cocktails.features.ingredient.repositories"
})
public class CocktailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CocktailsApplication.class, args);
        System.out.println("Hibernate Version = " + org.hibernate.Version.getVersionString());
        System.out.println("SpringBoot Version = " + org.springframework.boot.SpringBootVersion.getVersion());
        System.out.println("Spring Data Version = " + org.springframework.data.util.Version.javaVersion());
    }

}
