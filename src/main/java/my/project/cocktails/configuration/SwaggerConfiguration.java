package my.project.cocktails.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Велком ин батино апи")
                .description("Это апи делает все для моего счастья")
                .contact(new Contact("Leonid Kuzmychenko", "", ""))
                .license("Free chinese")
                .licenseUrl("")
                .version("Super-uber ultimate edition 1.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("my.project.cocktails"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo)
                .genericModelSubstitutes(Optional.class);
    }

}