package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.tacocloud.domain.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
