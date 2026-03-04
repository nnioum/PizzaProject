package order.model.pizza;

import java.util.HashSet;
import java.util.Set;

public class SlicedPizzaOrder extends PizzaOrder {
    private final Slice[] slices;

    public SlicedPizzaOrder(String pizzaSize, String doughName) {
        super(pizzaSize, doughName);
        this.slices = new Slice[this.getPizzaSize().getSliceNumber()];
    }

    public Slice[] getSlices() {
        return slices;
    }

    public static class Slice{
        private final Set<String> ingredients;

        public Slice() {
            this.ingredients = new HashSet<>();
        }

        public Set<String> getIngredients() {
            return ingredients;
        }
    }
}
