package order.model.pizza;

import java.util.HashSet;
import java.util.Set;

public class ReadyPizzaOrder extends PizzaOrder {

    public ReadyPizzaOrder(String pizzaSize, String doughName, String orderId) {
        super(pizzaSize, doughName, orderId, PizzaType.READY);
    }

    private String name;
    private Set<String> doubledIngredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getDoubledIngredients() {
        return doubledIngredients;
    }

    public void setDoubledIngredients(Set<String> doubledIngredients) {
        this.doubledIngredients = doubledIngredients;
    }

    @Override
    public String toString() {
        return "id - '" + id +
                "Тип пиццы - " + pizzaType+
                "Размер пиццы - " + pizzaSize +
                "Название пиццы - " + name +
                "Основа для пиццы - " + doughName +
                "Дублирующие ингредиенты - " + doubledIngredients +
                "Цена - " + price +
                "id Заказа - " + orderId;
    }
}
