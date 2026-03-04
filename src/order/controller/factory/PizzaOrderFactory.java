package order.controller.factory;

import exception.ValidationException;
import order.model.pizza.*;

public class PizzaOrderFactory {
    public PizzaOrder buildPizzaOrder(String pizzaType, String pizzaSize, String doughName, String orderId) throws ValidationException {
        PizzaType type = PizzaType.fromString(pizzaType);
        if (type == null) {
            throw new ValidationException("Тип пиццы " + pizzaType + " не существует");
        }
        return switch (type) {
            case READY -> new ReadyPizzaOrder(pizzaSize, doughName, orderId);
            case CUSTOM -> new CustomPizzaOrder(pizzaSize, doughName, orderId);
            case HALVED -> new HalvedPizzaOrder(pizzaSize, doughName, orderId);
            case SLICED -> new SlicedPizzaOrder(pizzaSize, doughName, orderId);
        };
    }
}
