package sia.tacocloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController - класс, обрабатывающий запросы
 * и возвращающий некоторую информацию.
 */
@Controller
public class HomeController {

    /**
     * Обрабатывает HTTP-запросы GET с корневым путем /
     * @return имя представления
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
