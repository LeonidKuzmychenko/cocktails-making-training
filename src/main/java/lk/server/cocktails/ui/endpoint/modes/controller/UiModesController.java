package lk.server.cocktails.ui.endpoint.modes.controller;

import com.google.gson.Gson;
import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.ui.endpoint.modes.dto.UiGameMode;
import lk.server.cocktails.ui.endpoint.modes.service.UiModesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ui/mode")
public class UiModesController {

    @Autowired
    private UiModesService uiModesService;

    @Autowired
    @Qualifier("Gson")
    private Gson gson;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUiModes(@RequestParam("locale") Locale locale) {
        List<UiGameMode> uiGameModes = uiModesService.getAll(locale);
        return new ResponseEntity<>(gson.toJson(uiGameModes), HttpStatus.OK);
    }
}
