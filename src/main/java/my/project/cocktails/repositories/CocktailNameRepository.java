package my.project.cocktails.repositories;

import my.project.cocktails.entities.CocktailName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailNameRepository extends JpaRepository<CocktailName, Long> {

}
