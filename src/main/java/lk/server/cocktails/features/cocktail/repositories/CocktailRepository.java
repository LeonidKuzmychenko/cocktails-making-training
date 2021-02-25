package lk.server.cocktails.features.cocktail.repositories;

import lk.server.cocktails.features.cocktail.entities.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

    @Query(value = "SELECT * from cocktail ORDER BY random() LIMIT :limit", nativeQuery = true)
    List<Cocktail> findRandomLimitCocktails(@Param("limit") int limit);

    @Query(value = "SELECT * from cocktail WHERE cocktail.cocktail_id NOT IN :exclude ORDER BY random() LIMIT 1", nativeQuery = true)
    Cocktail findRandomLimitCocktail(@Param("exclude") List<Long> exclude);

}
