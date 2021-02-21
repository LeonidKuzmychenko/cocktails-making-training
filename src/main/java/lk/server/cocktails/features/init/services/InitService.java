package lk.server.cocktails.features.init.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.customtypes.locale.LocaleService;
import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.cocktail.services.CocktailService;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.ingredient.services.IngredientService;
import lk.server.cocktails.features.init.dto.CocktailFileStructure;
import lk.server.cocktails.features.init.dto.IngredientFileStructure;
import lk.server.cocktails.features.init.dto.help.CocktailsTransformHelperFinish;
import lk.server.cocktails.features.init.dto.help.CocktailsTransformHelperStart;
import lk.server.cocktails.features.mix.services.MixCocktailService;
import lk.server.cocktails.features.modes.dto.GameModeDto;
import lk.server.cocktails.features.modes.entities.GameMode;
import lk.server.cocktails.features.modes.service.GameModeService;
import lk.utils.files.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InitService {
    @Autowired
    private CocktailService cocktailService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private MixCocktailService mixCocktailService;

    @Autowired
    private GameModeService gameModeService;

    @Autowired
    private FileManager fileManager;

    @Autowired
    private LocaleService localeService;

    @Autowired
    @Qualifier("Gson")
    private Gson gson;

    public void init() throws IOException {
        List<CocktailsTransformHelperStart> cocktailsTransformHelperStarts = readCocktails();
        Map<String, Long> savedIngredients = readIngredients();

        //Названия коктейлей преобразуются в их id-шники
        System.out.println("Названия коктейлей преобразуются в их id-шники (11)");
        List<CocktailsTransformHelperFinish> cocktailsTransformHelperFinishes = cocktailsTransformHelperStarts
                .stream()
                .map(it -> it.toCocktailsTransformHelperFinish(savedIngredients))
                .collect(Collectors.toList());

        //Связываются все коктейли с их игредиентами
        System.out.println("Связываются все коктейли с их игредиентами (12)");
        mixCocktailService.addIngredientsByHelperClass(cocktailsTransformHelperFinishes);


        //Режимы сохраняются в бд
        System.out.println("Режимы сохраняются в бд (13)");
        List<GameMode> gameModes = readModes();
        gameModeService.saveAll(gameModes);

        System.out.println("end init");
    }

    private List<CocktailsTransformHelperStart> readCocktails() throws IOException {
        //Считывание коктейтей из файла
        System.out.println("start init");
        System.out.println("Считывание коктейтей из файла (1)");
        String readCocktails = fileManager.readString("src/main/resources/db/cocktails.json");

        //Преобразование Json в список объектов коктейлей
        System.out.println("Преобразование Json в список объектов коктейлей (2)");
        Type type = new TypeToken<List<CocktailFileStructure>>() {
        }.getType();
        List<CocktailFileStructure> cocktailFileStructures = gson.fromJson(readCocktails, type);

        //Преобразование формата объекта для парсинга в формат для записили в бд
        System.out.println("Преобразование формата объекта для парсинга в формат для записили в бд (3)");
        List<Cocktail> cocktailList = cocktailFileStructures.stream().map(CocktailFileStructure::toCocktail).collect(Collectors.toList());

        //Запись коктейлей в бд и преобразование их в список с id-шниками
        System.out.println("Запись коктейлей в бд и преобразование их в список с id-шниками (4)");
        List<Cocktail> savedCocktails = cocktailService.saveAll(cocktailList);

        //Преобразование коктейлей в тип данных, где хранятся только id коктейля и названия ингредиентов на EN
        System.out.println("Преобразование коктейлей в тип данных, где хранятся только id коктейля и названия ингредиентов на EN (5)");
        return savedCocktails.stream()
                .map(it -> toCocktailsTransformHelperStart(cocktailFileStructures, it))
                .collect(Collectors.toList());
    }

    private Map<String, Long> readIngredients() throws IOException {
        //Считывание ингредиентов из файла
        System.out.println("Считывание ингредиентов из файла (6)");
        String readIngredients = fileManager.readString("src/main/resources/db/ingredients.json");

        //Преобразование Json в список объектов ингредиентов
        System.out.println("Преобразование Json в список объектов ингредиентов (7)");
        Type type = new TypeToken<List<IngredientFileStructure>>() {
        }.getType();
        List<IngredientFileStructure> cocktails = gson.fromJson(readIngredients, type);

        //Преобразование формата объекта для парсинга в формат для записили в бд
        System.out.println("Преобразование формата объекта для парсинга в формат для записили в бд (8)");
        List<Ingredient> cocktailsFinish = cocktails.stream()
                .map(IngredientFileStructure::toIngredient)
                .collect(Collectors.toList());

        //Запись ингредиентов в бд и преобразование их в список с id-шниками
        System.out.println("Запись ингредиентов в бд и преобразование их в список с id-шниками (9)");
        List<Ingredient> savedIngredients = ingredientService.saveAll(cocktailsFinish);

        //Создание мапы коктелей, где ключ - имя игредиента на английском, а значение - id
        System.out.println("Создание мапы коктелей, где ключ - имя игредиента на английском, а значение - id (10)");
        return savedIngredients.stream()
                .collect(Collectors.toMap(
                        key -> localeService.getStringByLocale(new ArrayList<>(key.getIngredientNames()), Locale.EN),
                        Ingredient::getIngredientId));
    }

    public List<GameMode> readModes() throws IOException {
        //Считывание режимов из файла
        System.out.println("Считывание режимов из файла (14)");
        String readGameModesDto = fileManager.readString("src/main/resources/db/modes.json");

        //Преобразование Json в список объектов режимов
        System.out.println("Преобразование Json в список объектов режимов (15)");
        Type type = new TypeToken<List<GameModeDto>>() {
        }.getType();
        List<GameModeDto> gameModesDto = gson.fromJson(readGameModesDto, type);

        //Преобразование формата объектов режимов для парсинга в формат для записили в бд
        System.out.println("Преобразование формата объектов режимов для парсинга в формат для записили в бд (16)");
        return gameModesDto.stream().map(GameModeDto::toGameModeName).collect(Collectors.toList());
    }

    public CocktailsTransformHelperStart toCocktailsTransformHelperStart(List<CocktailFileStructure> cocktailFileStructures, Cocktail cocktail) {
        CocktailsTransformHelperStart cocktailsTransformHelperStart = new CocktailsTransformHelperStart();
        cocktailsTransformHelperStart.setCocktailId(cocktail.getCocktailId());
        cocktailsTransformHelperStart.setIngredientNames(getIngredientListByName(
                cocktailFileStructures, localeService.getStringByLocale(
                        new ArrayList<>(cocktail.getCocktailName()), Locale.EN)));
        return cocktailsTransformHelperStart;
    }

    private List<String> getIngredientListByName(List<CocktailFileStructure> cocktailFileStructures, String name) {
        for (CocktailFileStructure cocktail : cocktailFileStructures)
            if (cocktail.getNameEN().equals(name))
                return cocktail.getIngredientStructures().stream()
                        .map(IngredientFileStructure::getNameEN)
                        .collect(Collectors.toList());
        return new ArrayList<>();
    }
}
