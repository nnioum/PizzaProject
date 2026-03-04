package order.model.pizza;

import java.util.Set;

public class CustomPizzaOrder extends PizzaOrder {

    public CustomPizzaOrder(String pizzaSize, String doughName) {
        super(pizzaSize, doughName);
    }

    private String displayName;
    private Set<String> ingredients;

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
}
