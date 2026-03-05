package request.handler.pizza_order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.PizzaOrderController;
import order.model.pizza.PizzaOrder;
import request.handler.pizza_order.sub_command.PizzaOrderSubCommandHandler;

import java.util.List;
import java.util.Map;

public class ListPizzaOrderCommandHandler extends PizzaOrderSubCommandHandler {
    private final PizzaOrderController pizzaOrderController = new PizzaOrderController();

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {
        Map<String, String> params = parseParams(subWord);
        String orderId = params.get("order-id");
        if(orderId==null){
            System.out.println("Параметр --order-id обязательный");
            return;
        }
        List<PizzaOrder> orderPizzaByOrderId = pizzaOrderController.getAllByOrderId(orderId);
        System.out.println(orderPizzaByOrderId);
    }
}
