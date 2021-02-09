package lk.server.cocktails.features.cocktail.repositories;

import lk.server.cocktails.features.cocktail.entities.CocktailName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailNameRepository extends JpaRepository<CocktailName, Long> {

}
