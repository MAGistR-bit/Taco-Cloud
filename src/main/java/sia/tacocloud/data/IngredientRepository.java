package sia.tacocloud.data;

import sia.tacocloud.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    /**
     * Получение всех ингредиентов в виде коллекции объектов
     *
     * @return коллекция объектов Ingredient
     */
    Iterable<Ingredient> findAll();

    /**
     * Получение одного ингредиента по идентификатору
     *
     * @param id идентификатор ингредиента
     * @return ингредиент, имеющий указанный идентификатор
     */
    Optional<Ingredient> findById(String id);

    /**
     * Сохранение объекта
     *
     * @param ingredient объект Ingredient, который необходимо сохранить
     * @return сохраненный объект Ingredient
     */
    Ingredient save(Ingredient ingredient);
}
