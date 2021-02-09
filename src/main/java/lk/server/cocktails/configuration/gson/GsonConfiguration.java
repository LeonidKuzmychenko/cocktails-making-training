package lk.server.cocktails.configuration.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfiguration {

    @Bean("Gson")
    public Gson getGson() {
        return new Gson();
    }

    @Bean("GsonExpose")
    public Gson getGsonExpose() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
}
