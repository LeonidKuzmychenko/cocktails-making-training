package lk.server.cocktails.database.cocktail.repositories;

import lk.server.cocktails.database.cocktail.entities.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

}
