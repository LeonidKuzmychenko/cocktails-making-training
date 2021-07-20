package lk.server.cocktails.database.modes.repositories;

import lk.server.cocktails.database.modes.entities.GameMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameModeRepository extends JpaRepository<GameMode, Long> {

}
