package sia.tacocloud;


import lombok.Data;

import java.util.List;

/**
 * Класс, представляющий рецепт
 */
@Data
public class Taco {
    private String name;
    private List<Ingredient> ingredients;

}
