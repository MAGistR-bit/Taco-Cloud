package sia.tacocloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тест будет запущен в контексте приложения Spring MVC.
 */
@WebMvcTest()
public class HomeControllerTest {

    /**
     * Используется объект MockMvc, для того
     * чтобы тест мог управлять фиктивным объектом.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Проверка домашней страницы
     * @throws Exception исключение, которое может возникнуть
     */
    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))                     // выполнить запрос GET /
                .andExpect(status().isOk())                     // ожидается код ответа HTTP 200
                .andExpect(view().name("home")) // ожидается имя представления home
                .andExpect(content().string(containsString("Welcome to...")));
    }

}