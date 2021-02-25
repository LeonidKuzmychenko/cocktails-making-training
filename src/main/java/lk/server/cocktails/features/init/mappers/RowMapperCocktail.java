package lk.server.cocktails.features.init.mappers;

import lk.server.cocktails.features.cocktail.entities.Cocktail;
import lk.server.cocktails.features.init.dto.CocktailDto;
import lk.utils.mapper.RowMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RowMapperCocktail {

    @Autowired
    protected RowMapperService<Cocktail> rowMapper;

    public Cocktail join(CocktailDto cocktailDto) {
        return rowMapper.join(new Cocktail(), cocktailDto);
    }
}