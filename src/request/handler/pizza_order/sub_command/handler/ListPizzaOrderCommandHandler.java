package request.handler.pizza_order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import request.handler.pizza_order.PizzaOrderCommandHandler;
import request.handler.pizza_order.sub_command.PizzaOrderSubCommandHandler;

public class ListPizzaOrderCommandHandler extends PizzaOrderSubCommandHandler {
    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {

    }
}
