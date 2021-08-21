package lk.server.cocktails.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StatisticDto {

    @JsonProperty("gameResults")
    private List<GameResult> gameResults = new ArrayList<>();

    @JsonProperty("libraryResults")
    private List<LibraryResult> libraryResults = new ArrayList<>();

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("region")
    private String region;

    @JsonProperty("uuid")
    private String uuid;

}