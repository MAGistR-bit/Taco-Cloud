package sia.tacocloud.data.jdbctemplate;

import sia.tacocloud.domain.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);


}
