package request.handler.pizza_order.sub_command;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.PizzaOrderController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class PizzaOrderSubCommandHandler {
    public abstract void handle(String... subWord) throws ValidationException, NotFoundException;

    protected Map<String, String> parseParams(String... subLineBlocks) throws ValidationException {
        if (subLineBlocks == null || subLineBlocks.length == 0) {
            return new HashMap<>();
        }
        for (String block : subLineBlocks) {
            if (!block.contains("=") || block.split("=").length <= 1) {
                throw new ValidationException("Некоректна введена команда " + block);
            }
        }
        return Arrays.stream(subLineBlocks)
                .map(s -> s.split("="))
                .collect(Collectors.toMap(a -> a[0].replace("--", ""), a -> a[1]));
    }
}