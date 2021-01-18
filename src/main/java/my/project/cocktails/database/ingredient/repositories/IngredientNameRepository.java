package my.project.cocktails.database.ingredient.repositories;

import my.project.cocktails.database.ingredient.entities.IngredientName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientNameRepository extends JpaRepository<IngredientName, Long> {

}
