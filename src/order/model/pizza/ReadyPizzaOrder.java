package order.model.pizza;

import java.util.Set;

public class ReadyPizzaOrder extends PizzaOrder {

    public ReadyPizzaOrder(String pizzaSize, String doughName) {
        super(pizzaSize, doughName);
    }

    private String name;
    private Set<String> doubledIngredients;
}
