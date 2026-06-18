package sia.tacocloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.domain.Ingredient;
import sia.tacocloud.domain.Ingredient.Type;
import sia.tacocloud.domain.Taco;
import sia.tacocloud.domain.TacoOrder;
import sia.tacocloud.data.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    /**
     * Добавляет ингредиенты в Model.
     * Model - объект, в котором данные пересылаются между
     * контроллером и представлением
     *
     * @param model объект Model
     */
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        // Получить ингредиенты из базы данных
        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientRepo.findAll()) {
            ingredients.add(ingredient);
        }

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
     *
     * @param taco      объект, который имеет свойства, взятые из формы
     * @param tacoOrder объект, который был помещен в модель методом order()
     * @return перенаправление на страницу
     */
    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        // Проверяем, имеются ли ошибке при заполнении объекта Taco
        if (errors.hasErrors()) {
            return "design";
        }

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
