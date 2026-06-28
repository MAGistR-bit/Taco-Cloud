package sia.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail
 * <p>Базовый класс конфигурации для Spring Security</p>
 */
@Configuration
public class SecurityConfig {

    /**
     * Бин-компонент, который будем использовать при создании новых
     * пользователей и аутентификации
     *
     * @return средство шифрования пароля - {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Создается список объектов {@link User}, каждый из которых
     * имеет свойство для хранения имени пользователя, пароля и списка привилегий.
     * <p>Используется служба для хранения учетных записей в памяти.</p>
     * @param passwordEncoder средство для шифрования паролей
     * @return служба для хранения учетных записей в памяти
     */
    @Bean
    public UserDetailsService inMemoryStorage(PasswordEncoder passwordEncoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User("buzz", passwordEncoder.encode("password"),
                List.of(new SimpleGrantedAuthority("ROLE_USER")))
        );

        usersList.add(new User("woody", passwordEncoder.encode("password"),
                List.of(new SimpleGrantedAuthority("ROLE_USER")))
        );

        return new InMemoryUserDetailsManager(usersList);
    }
}
