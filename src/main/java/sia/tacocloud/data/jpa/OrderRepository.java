package sia.tacocloud.data.jpa;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.domain.TacoOrder;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {

}
