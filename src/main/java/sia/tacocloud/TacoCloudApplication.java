package sia.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mikhail
 * Класс начальной загрузки приложения
 */
@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer {

    /**
     * Точка входа в программу. Этот метод будет вызываться
     * в момент запуска файла JAR.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
