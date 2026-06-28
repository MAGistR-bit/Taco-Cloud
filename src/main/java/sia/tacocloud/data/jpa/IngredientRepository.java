package sia.tacocloud.data.jpa;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.domain.Ingredient;

public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {
}
