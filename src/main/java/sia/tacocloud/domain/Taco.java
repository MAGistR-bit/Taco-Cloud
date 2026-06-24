package sia.tacocloud.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;

/**
 * Класс, представляющий рецепт
 */
@Data
@Table
@EqualsAndHashCode(exclude = "createdAt")
public class Taco {
    /**
     * Идентификатор объекта
     */
    @Id
    private Long id;

    /**
     * Дата и время сохранения объекта
     */
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients;

    public void addIngredient(Ingredient taco) {
        this.ingredients.add(new IngredientRef(taco.getId()));
    }
}
