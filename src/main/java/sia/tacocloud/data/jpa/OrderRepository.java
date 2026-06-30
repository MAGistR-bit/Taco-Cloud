package sia.tacocloud.data.jpa;

import org.springframework.data.domain.Pageable;import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.domain.TacoOrder;import sia.tacocloud.domain.User;import java.util.List;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {

    /**
     * Отображает список заказов, сделанных аутентифицированным пользователем
     * <p>Список заказов будет отображен в порядке убывания, т.е. от самого последнего
     * до самого первого.</p>
     * @param user пользователь
     * @param pageable количество заказов, которое должно быть отображено на веб-странице
     * @return список заказов пользователя
     */
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}
