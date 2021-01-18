package my.project.cocktails.database.cocktail.repositories;

import my.project.cocktails.database.cocktail.entities.CocktailDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailDescriptionRepository extends JpaRepository<CocktailDescription, Long> {

}
