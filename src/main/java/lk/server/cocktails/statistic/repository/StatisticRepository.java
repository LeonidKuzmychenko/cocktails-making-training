package lk.server.cocktails.statistic.repository;

import lk.server.cocktails.statistic.model.StatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<StatisticEntity, String> {

//    Optional<StatisticEntity> findByUuid(String uuid);
}
