## 🥁 Работа с конфигурацией
Окружение Spring поддерживает несколько источников свойств, в том числе:
* системные свойства JVM;
* переменные окружения операционной системы;
* аргументы командной строки;
* файлы конфигурационных свойств приложения.

![Environment](/src/main/resources/static/images/Environment.png)

Если необходимо, чтобы базовый контейнер сервлета приложения
принимал запросы на каком-либо порту, отличном от порта по умолчанию (`8080`),
следует указать в файле `src/main/resources/application.yml` следующее:
```yaml
server:
  port: 9090  # любой другой порт
```

Можно указать порт, используя аргумент командной строки:
```bash
java -jar tacocloud-SNAPSHOT.jar --server-port=9090
```

Можно установить изменения в переменной окружения операционной системы:
```env
export SERVER_PORT=9090
```

### 📻 Содержание
1. [Настройка источника данных](#-настройка-источника-данных)
2. [Настройка журналирования](#-настройка-журналирования)
3. [Конфигурационные свойства](#-конфигурационные-свойства)


### 🎩 Настройка источника данных
Если захочу использовать базу данных MySQL, то следует добавить свойства, которые
представлены ниже. Spring Boot использует эти данные при автоматической
настройке bean-компонента DataSource.

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost/tacocloud
    username: tacouser
    password: tacopassword
    driver-class-name: com.mysql.jdbc.Driver  # необязательное свойство
```

Компонент `DataSource` будет объединен в пул соединений `HikariCP`, если он доступен в пути к классам. 
Если нет, то Spring Boot отыщет и использует одну из следующих реализаций пула соединений, присутствующую в пути к классам:
* `Tomcat JDBC Connection Pool`;
* `Apache Commons DBCP2`.

Это единственные пулы соединений, доступные механизму автоматической конфигурации, 
но вы всегда можете явно настроить bean-компонент `DataSource` и 
использовать любую другую реализацию пула по вашему выбору.

Инициализация базы данных:
```yaml
spring:
  datasource:
    schema:
     - order-schema.sql
     - ingredient-schema.sql
    data:
     - ingredients.sql
```

Также можно проинициализировать БД, используя 
JNDI (Java Naming and Directory Interface). 
> В таком случае другие свойства
с настройками подключения к источнику данных будут игнорироваться.
> 
```yaml
spring:
  datasource:
    jndi-name: java:/comp/env/jdbc/tacoCloudDS
```

### 👑 Настройка журналирования
> По умолчанию Spring Boot использует механизм журналирования Logback
> и выводит информацию уровня INFO (в консоль).

Чтобы иметь максимально полный контроль над настройками журналирования,
можно создать файл `logback.xml` в корне пути к классам (в `src/main/resources`).

Чтобы задать уровень журналирования, следует определить свойство с префиксом
`logging.level`, за которым следует имя механизма журналирования, для которого
устанавливается уровень фиксируемых сообщений.

```yaml
logging:
  level:
    root: WARN
    org.framework.security: DEBUG
```

При наличии у приложения разрешения на запись в `/var/logs/` 
журналирование будет производиться в `/var/logs/TacoCloud.log`.

По умолчанию ротация файлов журналов производится, **как 
только они достигают размера 10 Мбайт**.

### 🎲 Конфигурационные свойства
Для создания конфигурационных свойств используется аннотация `@ConfigurationProperties`.
Пример использования представлен ниже.

```java
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
public class OrderProps {
    
    private ing pageSize = 20;
}
```

```yaml
taco:
  orders:
    pageSize: 10
```

Если необходимо добавить метаданные к своим созданным свойствам,
то следует создать в папке `src/main/resources/META-INF` файл
c именем `additional-spring-configuration-metadata.json`.

Его содержимое будет представлено ниже:
```json
{"properties": [
  {
    "name": "taco.orders.page-size",
    "type": "java.lang.Integer",
    "description": "Sets the maximum number of orders to display in a list."
  }
]}
```
После этого нужно будет подключить следующую зависимость в `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```