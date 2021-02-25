package lk.server.cocktails.configuration.rowmapper;

import lk.utils.mapper.RowMapperService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RowMapperConfiguration {

    @Bean
    public RowMapperService<?> provideRowMapperService() {
        return new RowMapperService<>();
    }

}
