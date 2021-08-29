package lk.server.cocktails.statistic.service;

import lk.server.cocktails.statistic.model.StatisticEntity;
import lk.server.cocktails.statistic.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticService {

    @Autowired
    private StatisticRepository repository;

    public Optional<StatisticEntity> getByUuid(String uuid) {
        return repository.findById(uuid);
    }

    public StatisticEntity saveOne(StatisticEntity stat) {
        StatisticEntity statisticEntity = repository.findById(stat.getStatisticId())
                .map(entity -> entity.merge(stat))
                .orElse(stat);
        return repository.save(statisticEntity);
    }

    public List<StatisticEntity> getAll() {
        return repository.findAll();
    }
}
