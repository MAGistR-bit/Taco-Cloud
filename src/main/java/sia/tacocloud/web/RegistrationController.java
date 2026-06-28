package sia.tacocloud.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.data.jpa.UserRepository;
import sia.tacocloud.security.RegistrationForm;

/**
 * @author Mikhail
 * <p>Контроллер регистрации нового пользователя</p>
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Возвращает логическое имя представления
     * @return логическое имя представления
     */
    @GetMapping
    public String registerForm() {
        return "registration";
    }

    /**
     * Обрабатывает форму, отправленную POST-запросом
     * @param form форма регистрации пользователя
     * @return переход на страницу для входа, если пользователь успешно зарегистрировался
     */
    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
