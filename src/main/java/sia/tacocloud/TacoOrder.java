package sia.tacocloud;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mikhail
 * Класс, представляющий заказ
 */
@Data
public class TacoOrder {
    /**
     * Название доставки
     */
    private String deliveryName;
    /**
     * Улица
     */
    private String deliveryStreet;
    /**
     * Город
     */
    private String deliveryCity;
    /**
     * Штат
     */
    private String deliveryState;
    /**
     * Почтовый индекс
     */
    private String deliveryZip;
    /**
     * Номер карты
     */
    private String ccNumber;
    /**
     * Срок действия
     */
    private String ccExpiration;
    /**
     * Код безопасности карты
     */
    private String ccCVV;
    /**
     * Заказ
     */
    private List<Taco> tacos = new ArrayList<>();

    /**
     * Добавляет шаурму в заказ
     * @param taco шаурма, которую нужно добавить в заказ
     */
    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

}
