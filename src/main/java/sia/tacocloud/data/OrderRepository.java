package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.tacocloud.domain.TacoOrder;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

}
