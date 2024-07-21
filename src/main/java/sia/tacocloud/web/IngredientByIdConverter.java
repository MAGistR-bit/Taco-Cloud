package sia.tacocloud.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.Ingredient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mikhail
 * Конвертер - любой класс, который реализует интерфейс Converter
 * с методом convert(), получающим значение одного типа и преобразующим
 * его в значение другого типа. В данном случае преобразуется
 * список строк ["FLTO", "GRBF"] в список объектов Ingredient
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        ingredientMap.put("COTO", new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        ingredientMap.put("CARN", new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        ingredientMap.put("TMTO", new Ingredient("ТМТО", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        ingredientMap.put("LETC", new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
        ingredientMap.put("CHED",  new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientMap.put("JACK",  new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        ingredientMap.put("SLSA",  new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
    }


    /**
     * Конвертирует строку в ингредиент ({@link Ingredient}
     * @param id идентификатор ингредиента (например, "FLTO")
     * @return объект {@link Ingredient}
     */
    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
