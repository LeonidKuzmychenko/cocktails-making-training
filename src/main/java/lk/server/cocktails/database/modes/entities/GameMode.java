package lk.server.cocktails.database.modes.entities;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game_mode")
public class GameMode {

    @Id
    @Column(name = "game_mode_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long gameModeId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Expose
    @NotNull
    private Set<GameModeName> gameModeNames;
}
