package request.handler.pizza_order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.PizzaOrderController;
import request.handler.pizza_order.sub_command.PizzaOrderCommand;
import request.handler.pizza_order.sub_command.PizzaOrderSubCommandHandler;

import java.util.*;

public class CreatePizzaOrderCommandHandler extends PizzaOrderSubCommandHandler {
    private final Set<String> requiredCommands = new HashSet<>();
    private final PizzaOrderController pizzaOrderController = new PizzaOrderController();

    public CreatePizzaOrderCommandHandler() {
        requiredCommands.addAll(Arrays.asList("order-id", "type-pizza", "size", "dough-name"));
    }

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {
        Map<String, String> params = parseParams(subWord);
        for (String param : requiredCommands) {
            if (!params.containsKey(param)) {
                System.out.println("Параметр " + param + " обязательный");
                return;
            }
        }
        String id = pizzaOrderController.create(
                params.get("order-id"),
                params.get("type-pizza"),
                params.get("size"),
                params.get("dough-name"));
        System.out.println("Создана пицца по id: " + id);
    }
}
