package lk.server.cocktails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CocktailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CocktailsApplication.class, args);
        System.out.println("Hibernate Version = " + org.hibernate.Version.getVersionString());
        System.out.println("SpringBoot Version = " + org.springframework.boot.SpringBootVersion.getVersion());
        System.out.println("Spring Data Version = " + org.springframework.data.util.Version.javaVersion());
    }

}
