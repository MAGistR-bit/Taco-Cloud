# 👾 Spring в действии
Исчерпывающее руководство по основным возможностям Spring,
написанное простым и ясным языком.

<img src="src/main/resources/static/images/Spring.jpg" alt="Spring в действии" width="600" height="800">

## Содержание
1. [Структура проекта Spring](#-структура-проекта-spring)
2. [Тестирование проекта](#-тестирование-проекта)
3. [Работа с контроллерами представлений](#-работа-с-контроллерами-представлений)
4. [База данных](#-база-данных)
5. [Электронные ресурсы](#-электронные-ресурсы)
6. [Шаблоны для создания представлений](#-шаблоны-для-создания-представлений)
7. [Spring Data](Spring-Data-JDBC.md)
8. [Spring Security](#-spring-security)
9. [Хранилище учетных записей в Spring](#-хранилище-учетных-записей-в-spring)
10. [Использование сторонних систем аутентификации](#-использование-сторонних-систем-аутентификации)
11. [Знай своего пользователя](docs/Security.md)
12. [Taco API](#-taco-api)

## 📁 Структура проекта Spring
Проект имеет типичную структуру проекта 
Maven или Gradle, в которой исходный код помещается
в каталог `src/main/java`, код тестов - в `src/test/java`,
а ресурсы, не являющиеся Java-ресурсами, — в `src/main/resources`.
Обратите внимание на следующие элементы внутри этой структуры:
+ _mvnw_ и _mvnw.cmd_ - это сценарии-обертки Maven, эти сценарии
можно использовать для создания нового проекта, даже если на 
компьютере не установлен Maven;
+ _pom.xml_ - это параметры сборки Maven;
+ _TacoCloudApplication.java_ - это основной класс Spring Boot,
который запускает проект;
+ _application.properties_ - это файл, который используется для
определения конфигурационных свойств;
+ _static_ - в эту папку можно поместить любой статический контент
  (изображения, таблицы стилей, JavaScript и т.д.), который должен
отображаться в браузере. Изначально в этой папке ничего нет;
+ _templates_ - тут мы размещаем файлы шаблонов, используемые для
отображения контента в браузере. Изначально в этой папке ничего нет.
+ _TacoCloudApplicationTests.java_ - это простой тестовый класс, проверяющий
успех загрузки контекста приложения Spring.

## 🤖 Тестирование проекта
Тестирование - важная часть разработки программного обеспечения.
Всегда можно протестировать проект вручную, создав его, а затем
запустив тестирование из командной строки:

```textmate
$ ./mvnw package
...
$ java -jar target/taco-cloud-0.0.1-SNAPSHOT.jar
```

> Если необходимо пропустить выполнение тестов, то следует воспользоваться
> командой `./mvnw clean package -DskipTests`

Или, благодаря использованию Spring Boot, плагин Spring Boot 
Maven упрощает этот шаг еще больше:
```textmate
$ ./mvnw spring-boot:run
```
Любые тестовые классы можно запустить из командной строки,
используя следующее заклинание Maven:
```textmate
$ ./mvnw test
```

## 👨🏻‍💻 Работа с контроллерами представлений
Любой класс конфигурации может реализовать интерфейс 
`WebMvcConfigurer` и переопределить метод `addViewController`.
Например, можно добавить объявление контроллера представления в 
загрузочный класс `TacoCloudApplication`:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
```

## 🛢️ База данных
Для этапа разработки используется встроенная база данных H2.
По умолчанию для базы данных генерируется случайное имя. Но это
затрудняет определение и ввод вручную URL базы данных, если по какой-то
причине необходимо подключиться к БД с помощью консоли H2
(которая при использовании Spring Boot DevTools доступна по адресу
http://localhost:8080/h2-console). В проекте определено имя базы данных.
Таким образом, для подключения к базе данных (в консоли H2) используется
следующий URL: "jdbc:h2:mem:tacocloud".

Подключение к базе данных:
```textmate
User Name: sa
Password: пустая строка
```

```yaml
  datasource:
    generate-unique-name: false
    name: tacocloud
```

## 🗂️ Шаблоны для создания представлений
| Механизм шаблонов       | Начальная зависимость Spring Boot    | 
|-------------------------|--------------------------------------|
| FreeMarker              | spring-boot-starter-freemarker       |
| Шаблоны Groovy          | spring-boot-starter-groovy-templates |
| Java Server Pages (JSP) | Нет (поддерживается Tomcat или Jetty |
| Mustache                | spring-boot-starter-mustache         |
| Thymeleaf               | spring-boot-starter-thymeleaf        |


## 🎉 Spring Security
Подключение Spring Security осуществляется при помощи следующей зависимости:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
После этого перед вами появится страница входа, предлагающая
пройти аутентификацию.

Для аутентификации нужно будет ввести **имя пользователя** и **пароль**.
* Имя пользователя - `user`
* Пароль будет сгенерирован случайным образом и записан в журнал работы
приложения.
Пример:
```textmate
Using generated security password: a021b0e5-381f-4f80-bb5d-35661b36ad71
```

**Средство для шифрования паролей (в Spring):**
* `BCryptPasswordEncoder` - применяет надежное шифрование bcrypt;
* `NoOpPasswordEncoder` - не применяет шифрование;
* `Pbkdf2PasswordEncoder` - применяет шифрование PBKDF2;
* `SCryptPasswordEncoder` - применяет шифрование Scrypt;
* `StandardPasswordEncoder` - применяет шифрование SHA-256 (фактически устарел).

### 🧑 Хранилище учетных записей в Spring
Чтобы настроить хранилище учетных записей пользователей для их аутентификации,
понадобится bean-компонент UserDetailsService.

Интерфейс UserDetailsService относительно прост и имеет всего один метод,
который нужно реализовать:

```java
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetailsService loadUserByUsername(String username) throws UsernameNotFoundException;
}
```

В Spring Security есть несколько готовых реализаций UserDetailsService:
1) хранилище учетных записей в памяти;
2) хранилище учетных записей JDBC;
3) хранилище учетных записей LDAP.

### 💎 Использование сторонних систем аутентификации
Вместо просьбы ввести учетные данные на странице входа,
специфичные для веб-сайта, они предлагают аутентифицироваться
через другой веб-сайт, такой как Facebook, где пользователь, возможно,
уже выполнил вход.

Этот тип аутентификации основан на OAuth2 или OpenID Connect (OIDC).
* OAuth2 - спецификация авторизации, которую можно использовать для аутентификации
через сторонний веб-сайт.
* OpenID Connect - это еще одна спецификация безопасности, основанная на OAuth2 и
предназначенная для формализации взаимодействий, происходящих в ходе
сторонней аутентификации.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```
Общий набор свойств, которые необходимо настроить, чтобы приложение действовало
как клиент OAuth2/OpenID Connect, выглядит следующим образом:
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          <oauth2 or openid provider name>:
            clientId: <client id> 
            clientSecret: <client secret>
            scope: <comma-separated list of requested scope>
```

## 👑 Создание контроллеров RESTful
Атрибут `consumes` определяет **формат входных данных в запросе**,
а `produces` - **формат возвращаемых клиенту данных**.

> PUT - это семантическая противоположность GET.

_**Метод PUT предназначен для полной замены ресурса**_, а не для его изменения.
Напротив, целью **_HTTP-метода PATCH является исправление или частичное изменение ресурса_**.

Если какое-либо из свойств заказа при отправке PUT-запроса будет отсутствовать,
то оно **будет затерто нулевым значением**.

## 🎁 Taco API
Как только приложение будет запущено, вы можете подключиться к API, 
используя утилиту `curl`.

+ **Получить список недавно приготовленных тако:**

```bash
$ curl localhost:8080/api/tacos?recent
```
Пример ответа:
```json
[{"id":2,"createdAt":"2026-07-01T09:29:54.562+00:00","name":"Bovine Bounty","ingredients":[{"id":"COTO","name":"Corn Tortilla","type":"WRAP"},{"id":"GRBF","name":"Ground Beef","type":"PROTEIN"},{"id":"CHED","name":"Cheddar","type":"CHEESE"},{"id":"JACK","name":"Monterrey Jack","type":"CHEESE"},{"id":"SRCR","name":"Sour Cream","type":"SAUCE"}]},{"id":1,"createdAt":"2026-07-01T09:29:54.531+00:00","name":"Carnivore","ingredients":[{"id":"FLTO","name":"Flour Tortilla","type":"WRAP"},{"id":"GRBF","name":"Ground Beef","type":"PROTEIN"},{"id":"CARN","name":"Carnitas","type":"PROTEIN"},{"id":"SRCR","name":"Sour Cream","type":"SAUCE"},{"id":"SLSA","name":"Salsa","type":"SAUCE"},{"id":"CHED","name":"Cheddar","type":"CHEESE"}]}]

```

+ **Получить тако по идентификатору:**
```bash
$ curl localhost:8080/api/tacos/2
```
Пример ответа:
```json
{"id":2,"createdAt":"2026-07-01T09:47:43.345+00:00","name":"Bovine Bounty","ingredients":[{"id":"COTO","name":"Corn Tortilla","type":"WRAP"},{"id":"GRBF","name":"Ground Beef","type":"PROTEIN"},{"id":"CHED","name":"Cheddar","type":"CHEESE"},{"id":"JACK","name":"Monterrey Jack","type":"CHEESE"},{"id":"SRCR","name":"Sour Cream","type":"SAUCE"}]}
```

+ **Создать новый тако:**
```bash
curl -v -X POST http://localhost:8080/api/tacos \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Betsy",
    "ingredients": [
      {"id": "LETC", "name": "Lettuce", "type": "VEGGIES"},
      {"id": "SRCR", "name": "Sour Cream", "type": "SAUCE"}
    ]
  }' 
```

Пример ответа:
```json
< Expires: 0
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Wed, 01 Jul 2026 12:38:10 GMT
<
{"id":4,"createdAt":"2026-07-01T10:38:10.518+00:00","name":"Betsy","ingredients":[{"id":"LETC","name":"Lettuce","type":"VEGGIES"},{"id":"SRCR","name":"Sour Cream","type":"SAUCE"}]}
```

+ **Частично обновить информацию о заказе:**
```bash
$ curl -v -X PATCH http://localhost:8080/api/orders/1   -H "Content-Type: application/json"   -d '{
    "deliveryName": "Betsy",
    "deliveryZip": "123456"
  }'
```

Пример ответа:
```json
* upload completely sent off: 62 bytes
< HTTP/1.1 200
        
{"id":1,"placedAt":"2026-07-01T12:13:08.898+00:00","user":{"id":1,"username":"jordon","password":"$2a$10$7Pl/lSs81IJDiXVffOysYuxXOeEimEW2zrWQYEhvZKeVHCpGPpSz6","fullName":"Craig Wa
lls","street":"123 North Street","city":"Cross Roads","state":"TX","zip":"76227","phoneNumber":"123-123-1234","enabled":true,"credentialsNonExpired":true,"accountNonExpired":true,"
authorities":[{"authority":"ROLE_USER"}],"accountNonLocked":true},"deliveryName":"Betsy","deliveryStreet":"123 North Street","deliveryCity":"Cross Roads","deliveryState":"TX","deli
veryZip":"123456","ccNumber":"4276080018320839","ccExpiration":"12/20","ccCVV":"456","tacos":[{"id":3,"createdAt":"2026-07-01","name":"Monterrey","ingredients":[
{"id":"FLTO","name":"Flour Tortilla","type":"WRAP"},{"id":"COTO","name":"Corn Tortilla","type":"WRAP"},{"id":"SLSA","name":"Salsa","type":"SAUCE"}]}]}
```

+ **Удалить тако по идентификатору:**
```bash
$ curl -v -X DELETE localhost:8080/api/orders/1
```
Пример ответа:
```textmate
* Request completely sent off
< HTTP/1.1 204 
< Vary: Origin
```

---

## 💻 Электронные ресурсы
1. Spring поддерживает API проверки JavaBean (также известный 
как JSR 303): https://jcp.org/en/jsr/detail?id=303
2. Аннотация @CreditCardNumber задействует алгоритм Луна:
   https://www.creditcardvalidator.org/articles/luhn-algorithm
3. Предметно-ориентированное проектирование (Domain-Driven Design, DDD):
https://www.dddcommunity.org/book/evans_2003/