package lk.server.cocktails.statistic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statistic_entity")
public class StatisticEntity {

    @Id
    @Column(name = "statistic_id")
    @JsonProperty("uuid")
    private String statisticId;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("region")
    private String region;

    @JsonProperty("gameResults")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<GameResult> gameResults;

    @JsonProperty("libraryResults")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<LibraryResult> libraryResults;

//    public StatisticEntity(String uuid, String locale, String region, Set<GameResult> gameResults, Set<LibraryResult> libraryResults) {
//        this.uuid = uuid;
//        this.locale = locale;
//        this.region = region;
//        this.gameResults = gameResults;
//        this.libraryResults = libraryResults;
//    }

    public StatisticEntity merge(StatisticEntity statistic) {
        if (locale == null || locale.trim().isEmpty()) {
            locale = statistic.getLocale();
        }
        if (region == null || region.trim().isEmpty()) {
            region = statistic.getRegion();
        }
        if (gameResults == null) {
            gameResults = new HashSet<>();
        }
        if (libraryResults == null) {
            libraryResults = new HashSet<>();
        }
        Set<GameResult> gameResults = statistic.getGameResults();
        if (gameResults != null && !gameResults.isEmpty()) {
            this.gameResults.addAll(gameResults);
        }
        Set<LibraryResult> libraryResults = statistic.getLibraryResults();
        if (libraryResults != null && !libraryResults.isEmpty()) {
            this.libraryResults.addAll(libraryResults);
        }
        return this;
    }
}