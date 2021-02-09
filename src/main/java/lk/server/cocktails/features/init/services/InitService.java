package lk.server.cocktails.features.init.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.cocktail.services.CocktailService;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.ingredient.services.IngredientService;
import lk.server.cocktails.features.init.dto.CocktailFileStructure;
import lk.server.cocktails.features.init.dto.IngredientFileStructure;
import lk.server.cocktails.features.init.dto.help.CocktailsTransformHelperFinish;
import lk.server.cocktails.features.init.dto.help.CocktailsTransformHelperStart;
import lk.server.cocktails.features.mix.services.MixCocktailService;
import lk.utils.files.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
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
    private FileManager fileManager;

    @Autowired
    @Qualifier("Gson")
    private Gson gson;

    public void init() throws IOException {
        List<CocktailsTransformHelperStart> cocktailsTransformHelperStarts = readCocktails();
        Map<String, Long> savedIngredients = readIngredients();

        //Названия коктейлей преобразуются в их id-шники
        List<CocktailsTransformHelperFinish> cocktailsTransformHelperFinishes = cocktailsTransformHelperStarts
                .stream()
                .map(it -> it.toCocktailsTransformHelperFinish(savedIngredients))
                .collect(Collectors.toList());

        //Связываются все коктейли с их игредиентами
        for (CocktailsTransformHelperFinish cocktailsTransformHelperFinish : cocktailsTransformHelperFinishes) {
            Long cocktailId = cocktailsTransformHelperFinish.getCocktailId();
            List<Long> ingredientIds = cocktailsTransformHelperFinish.getIngredientIds();
            for (Long ingredientId : ingredientIds) {
                mixCocktailService.addIngredient(cocktailId, ingredientId);
            }
        }
    }

    private List<CocktailsTransformHelperStart> readCocktails() throws IOException {
        //Считывание коктейтей из файла
        String readCocktails = fileManager.readString("src/main/resources/db/cocktails.json");

        //Преобразование Json в список объектов коктейлей
        Type type = new TypeToken<List<CocktailFileStructure>>() {
        }.getType();
        List<CocktailFileStructure> cocktailFileStructures = gson.fromJson(readCocktails, type);

        //Преобразование формата объекта для парсинга в формат для записили в бд
        List<Cocktail> cocktailList = cocktailFileStructures.stream().map(CocktailFileStructure::toCocktail).collect(Collectors.toList());

        //Запись коктейлей в бд и преобразование их в список с id-шниками
        List<Cocktail> savedCocktails = cocktailList.stream()
                .map(cocktail -> cocktailService.save(cocktail))
                .collect(Collectors.toList());

        //Преобразование коктейлей в тип данных, где хранятся только id коктейля и названия ингредиентов на EN
        return savedCocktails.stream()
                .map(it -> it.toCocktailsTransformHelperStart(cocktailFileStructures))
                .collect(Collectors.toList());
    }

    private Map<String, Long> readIngredients() throws IOException {
        //Считывание ингредиентов из файла
        String readIngredients = fileManager.readString("src/main/resources/db/ingredients.json");

        //Преобразование Json в список объектов ингредиентов
        Type type = new TypeToken<List<IngredientFileStructure>>() {
        }.getType();
        List<IngredientFileStructure> cocktails = gson.fromJson(readIngredients, type);

        //Преобразование формата объекта для парсинга в формат для записили в бд
        List<Ingredient> cocktailsFinish = cocktails.stream().map(IngredientFileStructure::toIngredient).collect(Collectors.toList());

        //Запись ингредиентов в бд и преобразование их в список с id-шниками
        List<Ingredient> savedIngredients = cocktailsFinish.stream()
                .map(ingredient -> ingredientService.save(ingredient))
                .collect(Collectors.toList());

        //Создание мапы коктелей, где ключ - имя игредиента на английском, а значение - id
        return savedIngredients.stream().collect(Collectors.toMap(Ingredient::getIngredientEnName, Ingredient::getIngredientId));
    }
}
