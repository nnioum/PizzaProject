package request.handler.order.sub_command.handler;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.OrderController;
import order.controller.view.OrderView;
import request.handler.order.sub_command.OrderSubCommandHandler;

import java.util.List;
import java.util.Map;

// Поля для фильтрации --status, --created-date-from, --created-date-to
// Поля для сортировки --sort-by (created-date, status)
public class ListCommandHandler extends OrderSubCommandHandler {
    private final OrderController orderController = new OrderController();

    @Override
    public void handle(String... subWord) throws ValidationException, NotFoundException {
        List<OrderView> all;
        if (subWord.length == 0) {
            all = orderController.getAll();
        } else {
            Map<String, String> stringStringMap = parseParams(subWord);
            all = orderController.getAll(stringStringMap.get("--status"), stringStringMap.get("--created-date-from"), stringStringMap.get("--created-date-to"), stringStringMap.get("--sort-by"));
        }

        for (OrderView order : all) {
            System.out.println(order);
        }
    }
}
