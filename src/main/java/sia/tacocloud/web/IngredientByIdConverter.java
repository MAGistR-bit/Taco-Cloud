package sia.tacocloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.domain.Ingredient;
import sia.tacocloud.data.jdbc.IngredientRepository;

/**
 * @author mikhail
 * Конвертер - любой класс, который реализует интерфейс Converter
 * с методом convert(), получающим значение одного типа и преобразующим
 * его в значение другого типа. В данном случае преобразуется
 * список строк ["FLTO", "GRBF"] в список объектов Ingredient
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    /**
     * Конвертирует строку в {@link Ingredient}
     * @param id идентификатор ингредиента (например, "FLTO")
     * @return объект {@link Ingredient}
     */
    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
