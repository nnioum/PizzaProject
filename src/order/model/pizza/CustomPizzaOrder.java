package order.model.pizza;

import java.util.HashSet;
import java.util.Set;

public class CustomPizzaOrder extends PizzaOrder {

    public CustomPizzaOrder(String pizzaSize, String doughName, String orderId) {
        super(pizzaSize, doughName, orderId, PizzaType.CUSTOM);
    }

    private String displayName;
    private Set<String> ingredients = new HashSet<>();

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "id - " + id +
                "\nТип пиццы - " + pizzaType +
                "\nРазмер пиццы - " + pizzaSize +
                "\nОснова для пиццы - '" + doughName +
                "\nИнгредиенты - " + String.join(", ", ingredients) +
                "\nЦена - " + price +
                "\nid Заказа - " + orderId;
    }
}
