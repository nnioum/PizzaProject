package request.handler.order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.OrderController;
import order.controller.view.OrderView;
import order.model.Order;
import request.handler.order.sub_command.OrderSubCommandHandler;

import java.util.List;

public class ListCommandHandler extends OrderSubCommandHandler {
    private final OrderController orderController = new OrderController();

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {
        List<OrderView> all = orderController.getAll();
        for(OrderView order: all){
            System.out.println(order);
        }
    }
}
