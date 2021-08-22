package lk.server.cocktails.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LibraryResult {

    @JsonProperty("cocktailName")
    private String cocktailName;

}