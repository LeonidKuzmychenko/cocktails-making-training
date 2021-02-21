package lk.server.cocktails.features.modes.entities;

import com.google.gson.annotations.Expose;
import lk.server.cocktails.customtypes.locale.Locale;
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
@Table(name = "GameModeName")
public class GameModeName {

    @Id
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
