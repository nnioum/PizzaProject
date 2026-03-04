package request.handler.order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.OrderController;
import request.handler.order.sub_command.OrderSubCommandHandler;

public class SubmitCommandHandler extends OrderSubCommandHandler {
    private final OrderController orderController = new OrderController();

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {
        if (subWord.length == 0) {
            throw new ValidationException("Параметр id обязательный");
        }
        String id = subWord[0];
        orderController.submit(id);
        System.out.println("Создание заказа " + id + " заверешен\nИзменить заказ больше невозможно");
    }
}
