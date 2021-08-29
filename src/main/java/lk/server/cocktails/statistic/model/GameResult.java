package lk.server.cocktails.statistic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statistic_game_result")
public class GameResult {

    @Id
    @Column(name = "game_result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameResultId;

    @JsonProperty("cocktailName")
    private String cocktailName;

    @JsonProperty("result")
    private Boolean result;

    public GameResult(String cocktailName, Boolean result) {
        this.cocktailName = cocktailName;
        this.result = result;
    }
}