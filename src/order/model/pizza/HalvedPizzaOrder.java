package order.model.pizza;

import java.util.Set;

public class HalvedPizzaOrder extends PizzaOrder {

    private final PizzaHalf leftHalf;
    private final PizzaHalf rightHalf;

    public HalvedPizzaOrder(String pizzaSize, String doughName, String orderId) {
        super(pizzaSize, doughName, orderId, PizzaType.HALVED);
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

        @Override
        public String toString() {
            return "Название пиццы - " + name +
                    "\nДублирующие ингредианты - \n" + doubledIngredients;
        }
    }

    @Override
    public String toString() {
        return "id='" + id +
                "\nТип пиццы - " + pizzaType +
                "\nРазмер пиццы - " + pizzaSize +
                "\nОснова для пиццы - " + doughName +
                "\nПервая половина пиццы - \n" + leftHalf +
                "\nВторая половина пиццы - \n" + rightHalf +
                "\nЦена - " + price +
                "\nid Заказа - " + orderId;
    }
}