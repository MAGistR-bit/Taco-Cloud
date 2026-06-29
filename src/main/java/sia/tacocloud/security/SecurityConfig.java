package sia.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.data.jpa.UserRepository;

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

    /*
      Создается список объектов {@link User}, каждый из которых
      имеет свойство для хранения имени пользователя, пароля и списка привилегий.
      <p>Используется служба для хранения учетных записей в памяти.</p>

      @param passwordEncoder средство для шифрования паролей
     * @return служба для хранения учетных записей в памяти
     */
//    @Bean
//    public UserDetailsService inMemoryStorage(PasswordEncoder passwordEncoder) {
//        List<UserDetails> usersList = new ArrayList<>();
//        usersList.add(new User("buzz", passwordEncoder.encode("password"),
//                List.of(new SimpleGrantedAuthority("ROLE_USER")))
//        );
//
//        usersList.add(new User("woody", passwordEncoder.encode("password"),
//                List.of(new SimpleGrantedAuthority("ROLE_USER")))
//        );
//
//        return new InMemoryUserDetailsManager(usersList);
//    }

    /**
     * Определяет, имеется ли учетная запись пользователя в базе данных
     *
     * @param userRepository репозиторий для поиска учетной записи
     * @return учетная запись пользователя или {@link UsernameNotFoundException}, если пользователь
     * не найден
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            sia.tacocloud.domain.User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    /**
     * Создает конфигурацию безопасности
     *
     * @param http построитель, который используется для настройки работы системы
     *             безопасности на веб-уровне
     * @return конфигурация безопасности
     * @throws Exception исключение, которое может возникнуть
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/design", "/orders/**").hasRole("USER")
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )

                // Отключаем CSRF для H2 Console (только для разработки!)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )

                // Разрешаем отображение H2 Console во фрейме
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                );

        return http.build();

    }
}
