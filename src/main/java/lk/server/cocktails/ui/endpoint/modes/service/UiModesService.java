package lk.server.cocktails.ui.endpoint.modes.service;

import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.customtypes.locale.LocaleService;
import lk.server.cocktails.features.modes.entities.GameMode;
import lk.server.cocktails.features.modes.service.GameModeService;
import lk.server.cocktails.ui.endpoint.modes.dto.UiGameMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UiModesService {

    @Autowired
    private GameModeService service;

    @Autowired
    private LocaleService localeService;

    public List<UiGameMode> getAll(Locale locale) {
        List<GameMode> gameModes = service.findAll();

        List<UiGameMode> uiGameModes = gameModes.stream().map(it -> {
            UiGameMode uiGameMode = new UiGameMode();
            uiGameMode.setId(it.getGameModeId());
            uiGameMode.setName(localeService.getStringByLocale(it.getGameModeNames(), locale));
            return uiGameMode;
        }).collect(Collectors.toList());

        return uiGameModes;
    }


}
