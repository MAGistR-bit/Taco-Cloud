package sia.tacocloud.data.jdbc;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.domain.TacoOrder;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {

}
