package my.project.cocktails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"my.project.cocktails.entities" })
@EnableJpaRepositories(basePackages = {"my.project.cocktails.repositories"})
public class CocktailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailsApplication.class, args);
	}

}
