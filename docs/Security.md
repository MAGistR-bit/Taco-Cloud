## 🌐 Знай своего пользователя
Часто недостаточно просто знать, что пользователь аутентифицирован и обладает
некоторыми привилегиями. Нередко бывает желательно знать о них чуть больше,
чтобы иметь возможность адаптировать приложение под их привычки и наклонности.

В Spring Security имеется несколько способов определить пользователя:
1) Внедрить объект `java.security.Principal` в метод контроллера;
2) Внедрить объект `org.springframework.security.core.Authentication` в метод
контроллера;
3) Использовать `org.springframework.security.core.context.SecurityContextHolder`,
чтобы получить контекст безопасности;
4) Внедрить параметр метода с аннотацией `@AuthenticationPrincipal`.

**Пример №1.**

```java
import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.domain.TacoOrder;

import java.security.Principal;

public String processOrder(@Valid TacoOrder order,
                           Errors errors,
                           SessionStatus sessionStatus,
                           Principal principal) {
    
    //...
    User user = userRepository.findByUsername(principal.getName());
    order.setUser(user);
    //...
}
```

**Пример №2.**
```java
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.domain.TacoOrder;import sia.tacocloud.domain.User;

public String processOrder(@Valid TacoOrder order,
                           Errors errors,
                           SessionStatus sessionStatus,
                           Authentication authentication) {
    
    //...
    User user = (User) authentication.getPrincipal();
    order.setUser(user);
    //...
}
```

**Пример №3.**
```java
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;import org.springframework.validation.Errors;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.domain.TacoOrder;import sia.tacocloud.domain.User;

public String processOrder(@Valid TacoOrder order,
                           Errors errors,
                           SessionStatus sessionStatus,
                           @AuthenticationPrincipal User user) {
    
    //...
    order.setUser(user);
    //...
}
```

**Пример №4.**
```java
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;import org.springframework.security.core.context.SecurityContextHolder;import org.springframework.validation.Errors;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.domain.TacoOrder;
import sia.tacocloud.domain.User;

public String processOrder(@Valid TacoOrder order,
                           Errors errors,
                           SessionStatus sessionStatus) {
    
    //...
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    //...
}
```