package lk.server.cocktails.features.modes.repository;

import lk.server.cocktails.features.modes.entities.GameModeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameModeNameRepository extends JpaRepository<GameModeName, Long> {

}
