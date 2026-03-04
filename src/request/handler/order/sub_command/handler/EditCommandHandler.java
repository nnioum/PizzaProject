package request.handler.order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.OrderController;
import request.handler.order.sub_command.OrderSubCommandHandler;

import java.util.Arrays;
import java.util.Map;

public class EditCommandHandler extends OrderSubCommandHandler {
    private final OrderController orderController = new OrderController();

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {
        if (subWord.length == 0) {
            throw new ValidationException("Параметр id обязательный");
        }
        String id = subWord[0];
        Map<String, String> params = parseParams(Arrays.copyOfRange(subWord, 1, subWord.length));
        orderController.update(id, params.get("--comment"), params.get("--scheduled-date"));
        System.out.println("Изменен заказ по id: " + id);
    }
}
