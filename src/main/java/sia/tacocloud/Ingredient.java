package sia.tacocloud;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author mikhail
 * Класс, представляющий ингредиенты тако
 */
@Data
public class Ingredient {
    /**
     * Идентификатор ингредиента
     */
    @Id
    private final String id;
    /**
     * Название ингредиента
     */
    private final String name;
    /**
     * Тип ингредиента
     */
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
