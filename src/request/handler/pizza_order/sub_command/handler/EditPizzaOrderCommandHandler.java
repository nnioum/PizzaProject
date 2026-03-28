package request.handler.pizza_order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.PizzaOrderController;
import request.handler.pizza_order.sub_command.PizzaOrderSubCommandHandler;

import java.util.Map;

public class EditPizzaOrderCommandHandler extends PizzaOrderSubCommandHandler {
    private final PizzaOrderController pizzaOrderController = new PizzaOrderController();

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {
        Map<String, String> paramByNames = parseParams(subWord);
        String id = paramByNames.get("id");
        if (id == null) {
            throw new ValidationException("Параметр --id обязательный");
        }
        paramByNames.remove("id");

        if (paramByNames.isEmpty()) {
            throw new ValidationException("Нет параметров для изменения. Укажите хотя бы один параметр для редактирования.");
        }

        pizzaOrderController.update(id, paramByNames);
        System.out.println("Пицца по id: " + id + " изменена");
    }
}