package sia.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mikhail
 * Класс начальной загрузки приложения
 */
@SpringBootApplication
public class TacoCloudApplication {

    /**
     * Точка входа в программу. Этот метод будет вызываться
     * в момент запуска файла JAR.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }
}
