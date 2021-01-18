package my.project.cocktails.database.ingredient.repositories;

import my.project.cocktails.database.ingredient.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
