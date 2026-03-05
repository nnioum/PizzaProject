package request.handler.order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.OrderController;
import order.controller.view.OrderView;
import order.model.Order;
import request.handler.order.sub_command.OrderSubCommandHandler;

import java.util.Map;

public class GetCommandHandler extends OrderSubCommandHandler {
    private final OrderController orderController = new OrderController();

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {
        if(subWord.length==0){
            throw new ValidationException("Параметр id обязательный");
        }
        Map<String, String> params = parseParams(subWord);
        String id = params.get("--id");
        OrderView orderView = orderController.getById(id);
        System.out.println(orderView);
    }
}
