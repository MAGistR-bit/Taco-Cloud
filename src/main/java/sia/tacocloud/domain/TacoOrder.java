package sia.tacocloud.domain;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mikhail
 * Класс, представляющий заказ
 */
@Data
@Table
public class TacoOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Date placedAt = new Date();

    /**
     * Название доставки
     */
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    /**
     * Улица
     */
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    /**
     * Город
     */
    @NotBlank(message = "City is required")
    private String deliveryCity;
    /**
     * Штат
     */
    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2, message = "State must be 2 characters")
    private String deliveryState;
    /**
     * Почтовый индекс
     */
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;
    /**
     * Номер карты
     */
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    /**
     * Срок действия
     */
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([2-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;
    /**
     * Код безопасности карты
     */
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    /**
     * Заказ
     */
    private List<Taco> tacos = new ArrayList<>();

    /**
     * Добавляет шаурму в заказ
     *
     * @param taco шаурма, которую нужно добавить в заказ
     */
    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

}
