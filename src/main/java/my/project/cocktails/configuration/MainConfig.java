package my.project.cocktails.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MainConfig {

    @Bean
    public DataSource dataSource(){

        String driverClassName = System.getenv("DATABASE_DRIVER_CLASS_NAME");
        String username = System.getenv("DATABASE_USERNAME");
        String password = System.getenv("DATABASE_PASSWORD");


        String host = System.getenv("DATABASE_HOST");
        String port = System.getenv("DATABASE_PORT");
        String name = System.getenv("DATABASE_NAME");
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;

        System.out.println(username);
        System.out.println(password);
        System.out.println(url);
        System.out.println(driverClassName);

        return DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(url)
                .driverClassName(driverClassName)
                .build();
    }

}