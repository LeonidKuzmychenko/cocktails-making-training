package my.project.cocktails.database.ingredient.repositories;

import my.project.cocktails.database.ingredient.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query(value = "SELECT * from ingredient WHERE ingredient_id NOT IN :exclude ORDER BY random() LIMIT :limit",
            nativeQuery = true)
    List<Ingredient> findRandomIngredientsWithExcludeAndLimitsIds(
            @Param("exclude") List<Long> exclude,
            @Param("limit") int limit);

}
