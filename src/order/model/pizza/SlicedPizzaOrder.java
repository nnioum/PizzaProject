package order.model.pizza;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SlicedPizzaOrder extends PizzaOrder {
    private final Slice[] slices;

    public SlicedPizzaOrder(String pizzaSize, String doughName, String orderId) {
        super(pizzaSize, doughName, orderId, PizzaType.SLICED);
        this.slices = new Slice[this.getPizzaSize().getSliceNumber()];
    }

    public Slice[] getSlices() {
        return slices;
    }

    public static class Slice {
        private final Set<String> ingredients;

        public Slice() {
            this.ingredients = new HashSet<>();
        }

        public Set<String> getIngredients() {
            return ingredients;
        }
    }

    @Override
    public String toString() {
        return "\nid - " + id + '\'' +
                "\nРазмер пиццы - " + pizzaSize +
                "\nТип пиццы" + pizzaType +
                "\nОснова для пиццы - " + doughName +
                "\nКусочки  - \n" + Arrays.toString(slices) +
                "\nЦена - " + price +
                "\nid заказа - " + orderId;
    }
}
