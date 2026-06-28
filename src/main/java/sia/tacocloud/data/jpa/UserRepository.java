package sia.tacocloud.data.jpa;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Поиск учетной записи по имени пользователя
     * @param username имя пользователя
     * @return учетная запись пользователя
     */
    User findByUsername(String username);
}
