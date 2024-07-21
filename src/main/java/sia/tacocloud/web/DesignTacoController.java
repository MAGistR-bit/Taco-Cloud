package sia.tacocloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.Ingredient;
import sia.tacocloud.Ingredient.Type;
import sia.tacocloud.Taco;
import sia.tacocloud.TacoOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    /**
     * Добавляет ингредиенты в Model.
     * Model - объект, в котором данные пересылаются между
     * контроллером и представлением
     *
     * @param model объект Model
     */
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("ТМТО", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        // Получить типы ингредиентов
        Type[] types = Ingredient.Type.values();
        // Добавить данные в Model
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    /**
     * Создать заказ для размещения в модели
     *
     * @return заказ клиента
     */
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    /**
     * Создать объект Taco для размещения в модели
     *
     * @return объект Taco
     */
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    /**
     * Обрабатывает GET-запрос с путем /design
     *
     * @return логическое представление (design.html)
     */
    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    /**
     * Отправляет рецепт тако
     * @param taco объект, который имеет свойства, взятые из формы
     * @param tacoOrder объект, который был помещен в модель методом order()
     * @return перенаправление на страницу
     */
    @PostMapping
    public String processTaco(Taco taco,
                            @ModelAttribute TacoOrder tacoOrder) {
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    /**
     * Фильтрация ингредиентов по типам
     *
     * @param ingredients список ингредиентов
     * @param type        тип ингредиента (белки, сыр, соус и др.)
     * @return отфильтрованные ингредиенты
     */
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients,
                                              Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
