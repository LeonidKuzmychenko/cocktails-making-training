package lk.server.cocktails.features.modes.repositories;

import lk.server.cocktails.features.modes.entities.GameMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameModeRepository extends JpaRepository<GameMode, Long> {

}
