package request.handler.pizza_order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.PizzaOrderController;
import request.handler.pizza_order.sub_command.PizzaOrderSubCommandHandler;

public class EditPizzaOrderCommandHandler extends PizzaOrderSubCommandHandler {
    private final PizzaOrderController pizzaOrderController = new PizzaOrderController();

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {

    }
}
