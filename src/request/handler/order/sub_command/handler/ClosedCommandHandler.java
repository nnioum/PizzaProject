package request.handler.order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.OrderController;
import request.handler.order.sub_command.OrderSubCommandHandler;

import java.util.Map;

public class ClosedCommandHandler extends OrderSubCommandHandler {
    private final OrderController orderController = new OrderController();

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {
        Map<String, String> params = parseParams(subWord);
        orderController.closed(params.get("--id"));
        System.out.println("Ваш заказ опрлачен, ожидайте");
    }
}
