package lk.server.cocktails.features.cocktail.repositories;

import lk.server.cocktails.features.cocktail.entities.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

}
