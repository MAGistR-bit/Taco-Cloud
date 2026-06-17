# 👾 Spring в действии
Исчерпывающее руководство по основным возможностям Spring,
написанное простым и ясным языком.

<img src="src/main/resources/static/images/Spring.jpg" alt="Spring в действии" width="600" height="800">

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


## 💻 Электронные ресурсы
1. Spring поддерживает API проверки JavaBean (также известный 
как JSR 303): https://jcp.org/en/jsr/detail?id=303
2. Аннотация @CreditCardNumber задействует алгоритм Луна:
   https://www.creditcardvalidator.org/articles/luhn-algorithm
3. Предметно-ориентированное проектирование (Domain-Driven Design, DDD):
https://www.dddcommunity.org/book/evans_2003/