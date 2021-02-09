package lk.server.cocktails.features.ingredient.repositories;

import lk.server.cocktails.features.ingredient.entities.IngredientName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientNameRepository extends JpaRepository<IngredientName, Long> {

}
