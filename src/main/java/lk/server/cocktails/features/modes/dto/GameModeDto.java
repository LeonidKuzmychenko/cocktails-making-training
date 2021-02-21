package lk.server.cocktails.features.modes.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.features.modes.entities.GameMode;
import lk.server.cocktails.features.modes.entities.GameModeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameModeDto {

    @SerializedName("nameEN")
    @Expose
    private String nameEN;

    @SerializedName("nameRU")
    @Expose
    private String nameRU;

    public GameMode toGameModeName(){
        GameMode gameMode = new GameMode();
        Set<GameModeName> gameModeNames = new HashSet<GameModeName>() {{
            add(new GameModeName(Locale.EN, getNameEN()));
            add(new GameModeName(Locale.RU, getNameRU()));
        }};
        gameMode.setGameModeNames(gameModeNames);
        return gameMode;
    }
}