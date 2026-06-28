package sia.tacocloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sia.tacocloud.data.jpa.IngredientRepository;
import sia.tacocloud.web.HomeController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Тест будет запущен в контексте приложения Spring MVC.
 */
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    /**
     * Используется объект MockMvc, для того
     * чтобы тест мог управлять фиктивным объектом.
     */
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientRepository ingredientRepository;

    /**
     * Проверка домашней страницы
     * @throws Exception исключение, которое может возникнуть
     */
    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/home"))                     // выполнить запрос GET /
                .andExpect(status().isOk())                         // ожидается код ответа HTTP 200
                .andExpect(view().name("home"))     // ожидается имя представления home
                .andExpect(content().string(containsString("Welcome to...")));
    }

}