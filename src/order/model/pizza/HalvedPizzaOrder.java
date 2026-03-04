package order.model.pizza;

import java.util.Set;

public class HalvedPizzaOrder extends PizzaOrder {

    private final PizzaHalf leftHalf;
    private final PizzaHalf rightHalf;

    public HalvedPizzaOrder(String pizzaSize, String doughName) {
        super(pizzaSize, doughName);
        this.leftHalf = new PizzaHalf();
        this.rightHalf = new PizzaHalf();
    }

    public PizzaHalf getLeftHalf() {
        return leftHalf;
    }

    public PizzaHalf getRightHalf() {
        return rightHalf;
    }

    public static class PizzaHalf {
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
    }
}