package lk.server.cocktails.features.init.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lk.server.cocktails.customtypes.locale.Locale;
import lk.server.cocktails.features.cocktail.entities.*;
import lk.server.cocktails.features.cocktail.services.CocktailService;
import lk.server.cocktails.features.ingredient.entities.Ingredient;
import lk.server.cocktails.features.ingredient.entities.IngredientName;
import lk.server.cocktails.features.ingredient.services.IngredientService;
import lk.server.cocktails.features.init.dto.CocktailFileStructure;
import lk.server.cocktails.features.init.dto.IngredientFileStructure;
import lk.server.cocktails.features.init.dto.IngredientStructure;
import lk.server.cocktails.features.init.dto.help.SavedCocktailsDto;
import lk.server.cocktails.features.init.dto.help.SavedCocktailsDto2;
import lk.server.cocktails.features.mix.services.MixCocktailService;
import lk.utils.files.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cocktails")
public class InitCocktailController {

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

    @GetMapping(value = "/initIngredients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> init() throws IOException {
        List<SavedCocktailsDto> savedCocktailsDtos = readCocktails();
        Map<String, Long> savedIngredients = readIngredients();

        List<SavedCocktailsDto2> savedCocktailsDto2List = savedCocktailsDtos.stream().map(it -> {
            SavedCocktailsDto2 savedCocktailsDto2 = new SavedCocktailsDto2();
            savedCocktailsDto2.setCocktailId(it.getCocktailId());
            savedCocktailsDto2.setCocktailName(it.getCocktailName());
            List<Long> ings = it.getIngredientNames().stream().map(savedIngredients::get).collect(Collectors.toList());
            savedCocktailsDto2.setIngredientIds(ings);
            return savedCocktailsDto2;
        }).collect(Collectors.toList());

        savedCocktailsDto2List
                .forEach(savedCocktailsDto2 -> savedCocktailsDto2.getIngredientIds()
                        .forEach(aLong -> mixCocktailService.addIngredient(savedCocktailsDto2.getCocktailId(), aLong)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<SavedCocktailsDto> readCocktails() throws IOException {
        //считывание коктелей из бд
        String readCocktails = fileManager.readString("src/main/resources/db/cocktails.json");
        Type type = new TypeToken<List<CocktailFileStructure>>() {
        }.getType();
        List<CocktailFileStructure> cocktails = gson.fromJson(readCocktails, type);

        //конвертация типа под запись в бд
        List<Cocktail> cocktailList = cocktails.stream().map(it -> {
            Cocktail cocktail = new Cocktail();
            cocktail.setImage("");
            cocktail.setCocktailName(new HashSet<CocktailName>() {{
                add(new CocktailName(Locale.RU, it.getNameRU()));
                add(new CocktailName(Locale.EN, it.getNameEN()));
            }});
            cocktail.setCocktailAssociation(new HashSet<CocktailAssociation>() {{
                add(new CocktailAssociation(Locale.RU, it.getAssociationRU()));
                add(new CocktailAssociation(Locale.EN, it.getAssociationEN()));
            }});
            cocktail.setCocktailType(new HashSet<CocktailType>() {{
                add(new CocktailType(Locale.RU, it.getTypeRU()));
                add(new CocktailType(Locale.EN, it.getTypeEN()));
            }});
            cocktail.setCocktailMethod(new HashSet<CocktailMethod>() {{
                add(new CocktailMethod(Locale.RU, it.getMethodRU()));
                add(new CocktailMethod(Locale.EN, it.getMethodEN()));
            }});
            cocktail.setCocktailNote(new HashSet<CocktailNote>() {{
                add(new CocktailNote(Locale.RU, it.getNoteRU()));
                add(new CocktailNote(Locale.EN, it.getNoteEN()));
            }});
            cocktail.setCocktailGarnish(new HashSet<CocktailGarnish>() {{
                add(new CocktailGarnish(Locale.RU, it.getGarnishRU()));
                add(new CocktailGarnish(Locale.EN, it.getGarnishEN()));
            }});
            return cocktail;
        }).collect(Collectors.toList());

        //запись в бд
        cocktailList.forEach(cocktail -> cocktailService.save(cocktail));

        //считывание с бд
        List<Cocktail> savedCocktails = cocktailService.findAll();

        //преобразование типа
        List<SavedCocktailsDto> savedCocktailsDtos = savedCocktails.stream().map(it -> {
            SavedCocktailsDto savedCocktailsDto = new SavedCocktailsDto();
            savedCocktailsDto.setCocktailId(it.getCocktailId());
            String name = null;
            for (CocktailName nameThis : it.getCocktailName()) {
                if (nameThis.getLocale() == Locale.EN)
                    name = nameThis.getName();
            }
            savedCocktailsDto.setCocktailName(name);
            savedCocktailsDto.setIngredientNames(getIngredientListByName(cocktails, name));
            return savedCocktailsDto;
        }).collect(Collectors.toList());
        return savedCocktailsDtos;
    }

    private List<String> getIngredientListByName(List<CocktailFileStructure> cocktails, String name) {
        for (CocktailFileStructure cocktail : cocktails) {
            if (cocktail.getNameEN().equals(name)) {
                return cocktail.getIngredientStructures().stream()
                        .map(IngredientStructure::getNameEN)
                        .collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }

    private Map<String, Long> readIngredients() throws IOException {
        //считывание ингредиентов из бд
        String readIngredients = fileManager.readString("src/main/resources/db/ingredients.json");
        Type type = new TypeToken<List<IngredientFileStructure>>() {
        }.getType();
        List<IngredientFileStructure> cocktails = gson.fromJson(readIngredients, type);

        //все ингредиенты из файла в нужном формате
        List<Ingredient> cocktailsFinish = cocktails.stream().map(it -> {
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientNames(new HashSet<IngredientName>() {{
                add(new IngredientName(Locale.RU, it.getNameRU()));
                add(new IngredientName(Locale.EN, it.getNameEN()));
            }});
            return ingredient;
        }).collect(Collectors.toList());

        //сохранение в бд
        cocktailsFinish.forEach(ingredient -> ingredientService.save(ingredient));

        //получение из бд
        List<Ingredient> savedIngredients = ingredientService.findAll();

        //создание списка коктелей, где ключ - имя, а значение - id
        Map<String, Long> ingredientsMap = savedIngredients.stream().collect(Collectors.toMap(key -> {
            for (IngredientName name : key.getIngredientNames())
                if (name.getLocale() == Locale.EN)
                    return name.getName();
            System.out.println("ERROR");
            return null;
        }, Ingredient::getIngredientId));

        return ingredientsMap;
    }


}