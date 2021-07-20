package lk.server.cocktails.database.modes.entities;

import com.google.gson.annotations.Expose;
import lk.server.cocktails.locale.ILocalization;
import lk.server.cocktails.locale.Locale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game_mode_name")
public class GameModeName implements ILocalization {

    @Id
    @Column(name = "game_mode_name_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long gameModeNameId;

    @Column
    @Expose
    private Locale locale;

    @Column
    @Expose
    private String name;

    public GameModeName(Locale locale, String name) {
        this.locale = locale;
        this.name = name;
    }
}
