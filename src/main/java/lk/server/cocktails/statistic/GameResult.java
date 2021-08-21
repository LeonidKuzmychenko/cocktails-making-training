package lk.server.cocktails.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GameResult {

    @JsonProperty("cocktailName")
    private String cocktailName;

    @JsonProperty("result")
    private Boolean result;

}