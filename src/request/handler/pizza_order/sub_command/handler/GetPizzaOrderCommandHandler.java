package request.handler.pizza_order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.PizzaOrderController;
import order.model.pizza.PizzaOrder;
import request.handler.pizza_order.sub_command.PizzaOrderSubCommandHandler;

import java.util.Map;

public class GetPizzaOrderCommandHandler extends PizzaOrderSubCommandHandler {
    private final PizzaOrderController pizzaOrderController = new PizzaOrderController();

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {
        Map<String, String> params = parseParams(subWord);
        String id = params.get("--id");
        if (id == null) {
            System.out.println("Параметр --id обязательный");
            return;
        }
        PizzaOrder pizzaOrder = pizzaOrderController.getById(id);
        System.out.println(pizzaOrder);
    }
}
