package my.project.cocktails.configuration.files;

import lk.utils.files.FileManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileManagerConfiguration {
    @Bean
    public FileManager getFileManager(){
        return new FileManager();
    }
}
