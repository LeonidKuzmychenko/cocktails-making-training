package lk.server.cocktails.features.init.services;

import lk.server.cocktails.database.cocktail.entities.*;
import lk.server.cocktails.database.ingredient.entities.Ingredient;
import lk.server.cocktails.database.ingredient.entities.IngredientName;
import lk.server.cocktails.database.modes.entities.GameMode;
import lk.server.cocktails.database.modes.entities.GameModeName;
import lk.server.cocktails.features.init.dto.CocktailDto;
import lk.server.cocktails.features.init.dto.InitDbDto;
import lk.server.cocktails.features.init.mappers.RowMapperCocktailDto;
import lk.server.cocktails.features.init.services.parent.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReadDbByWebService extends InitDbService {

    @Autowired
    private RowMapperCocktailDto rowMapperCocktailDto;

    public InitDbDto read() {
        InitDbDto initDbDto = new InitDbDto();
        System.out.println("getGameModes");
        initDbDto.setGameModes(readGameModes());
        System.out.println("getIngredients");
        initDbDto.setIngredients(readIngredients());
        System.out.println("getCocktails");
        initDbDto.setCocktailsDto(readCocktails());
        return initDbDto;
    }

    //TODO некрасиво
    public List<GameMode> readGameModes() {
        return gameModeService.findAll().stream()
                .peek(it -> it.setGameModeId(null))
                .peek(it -> {
                    Set<GameModeName> gameModeNames = it.getGameModeNames().stream()
                            .peek(it2 -> it2.setGameModeNameId(null))
                            .collect(Collectors.toSet());
                    it.setGameModeNames(gameModeNames);
                })
                .collect(Collectors.toList());
    }

    //TODO некрасиво
    public List<Ingredient> readIngredients() {
        return ingredientService.findAll().stream()
                .peek(it -> it.setIngredientId(null))
                .peek(it -> {
                    Set<IngredientName> ingredientNames = it.getIngredientNames().stream()
                            .peek(it2 -> it2.setIngredientNameId(null))
                            .collect(Collectors.toSet());
                    it.setIngredientNames(ingredientNames);
                })
                .collect(Collectors.toList());
    }

    //TODO некрасиво
    public List<CocktailDto> readCocktails() {
        return cocktailService.findAll().stream()
                .map(it -> rowMapperCocktailDto.join(it))
                .peek(it -> {
                    Set<CocktailName> cocktailNames = it.getCocktailName().stream()
                            .peek(it2 -> it2.setCocktailNameId(null))
                            .collect(Collectors.toSet());
                    it.setCocktailName(cocktailNames);

                    Set<CocktailAssociation> cocktailAssociations = it.getCocktailAssociation().stream()
                            .peek(it2 -> it2.setCocktailAssociationId(null))
                            .collect(Collectors.toSet());
                    it.setCocktailAssociation(cocktailAssociations);

                    Set<CocktailType> cocktailTypes = it.getCocktailType().stream()
                            .peek(it2 -> it2.setCocktailTypeId(null))
                            .collect(Collectors.toSet());
                    it.setCocktailType(cocktailTypes);

                    Set<CocktailMethod> cocktailMethods = it.getCocktailMethod().stream()
                            .peek(it2 -> it2.setCocktailMethodId(null))
                            .collect(Collectors.toSet());
                    it.setCocktailMethod(cocktailMethods);

                    Set<CocktailNote> cocktailNotes = it.getCocktailNote().stream()
                            .peek(it2 -> it2.setCocktailNoteId(null))
                            .collect(Collectors.toSet());
                    it.setCocktailNote(cocktailNotes);

                    Set<CocktailGarnish> cocktailGarnishes = it.getCocktailGarnish().stream()
                            .peek(it2 -> it2.setCocktailGarnishId(null))
                            .collect(Collectors.toSet());
                    it.setCocktailGarnish(cocktailGarnishes);
                })
                .collect(Collectors.toList());
    }

}
